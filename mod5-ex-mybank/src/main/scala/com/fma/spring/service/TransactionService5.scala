package com.fma.spring.service

import com.fma.spring.model.Transaction5
import com.fma.spring.service.TransactionService5.ROW_MAPPER
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.core.env.Environment
import org.springframework.jdbc.core.{JdbcTemplate, RowMapper}
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

import java.sql.{Connection, ResultSet, Statement}
import java.util.concurrent.CopyOnWriteArrayList
import java.util.{UUID, List => JList}
import javax.annotation.PostConstruct
import scala.beans.BeanProperty

@Component
class TransactionService5 {
  @BeanProperty
  @Autowired
  var jdbcTemplate: JdbcTemplate = _
  @BeanProperty
  @Autowired
  var env: Environment = _
  @BeanProperty
  @Value("${bank.slogan}")
  var bankSlogan: String = _

  private val transactions = new CopyOnWriteArrayList[Transaction5]()

  @PostConstruct
  def postConstruct(): Unit = {
    if (env.getActiveProfiles.contains("dev")) {
      System.out.println("DEV profile active, adding dummy data.")
      create(50.0, "ref1", "user1")
      create(60.0, "ref2", "user2")
    }
  }

  @Transactional
  def findByReceivingUser(userId: String): JList[Transaction5] = {
    System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive)
    jdbcTemplate.query("select id, amount, reference, bank_slogan, receiving_user from transactions where receiving_user = ?",
      ROW_MAPPER, userId)
  }

  @Transactional
  def findAll(): JList[Transaction5] = {
    System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive)
    jdbcTemplate.query("select id, amount, reference, bank_slogan, receiving_user from transactions", ROW_MAPPER)
  }

  @Transactional
  def create(amount: BigDecimal, reference: String, receivingUser: String): Transaction5 = {
    System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive)
    val keyHolder = new GeneratedKeyHolder()

    jdbcTemplate.update((conn: Connection) => {
      val preparedStatement = conn.
        prepareStatement("insert into transactions (amount, reference, bank_slogan, receiving_user) values (?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS)
      preparedStatement.setBigDecimal(1, amount.bigDecimal)
      preparedStatement.setString(2, reference)
      preparedStatement.setString(3, bankSlogan)
      preparedStatement.setString(4, receivingUser)
      preparedStatement
    }, keyHolder)

    val uuid = Option(keyHolder.getKeys.values)
      .map(_.iterator.next.asInstanceOf[UUID].toString)
      .orNull

    val transaction = Transaction5(amount, reference, bankSlogan, receivingUser)
    transaction.setId(uuid)
    transaction
  }
}

object TransactionService5 {
  final val ROW_MAPPER: RowMapper[Transaction5] = (resultSet: ResultSet, _: Int) => {
    val transaction = new Transaction5()
    transaction.id = resultSet.getObject("id").toString
    transaction.amount = resultSet.getBigDecimal("amount")
    transaction.reference = resultSet.getString("reference")
    transaction.bankSlogan = resultSet.getString("bank_slogan")
    transaction.receivingUser = resultSet.getString("receiving_user")
    transaction
  }
}
