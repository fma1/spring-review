package com.fma.spring.service

import com.fma.spring.model.Transaction7
import com.fma.spring.service.TransactionService7.ROW_MAPPER
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.{JdbcTemplate, RowMapper}
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

import java.sql.{Connection, ResultSet, Statement}
import java.util.concurrent.CopyOnWriteArrayList
import java.util.{UUID, List => JList}
import scala.beans.BeanProperty

@Component
class TransactionService7(jdbcTemplate: JdbcTemplate) {
  @BeanProperty
  @Value("${bank.slogan}")
  var bankSlogan: String = _

  @Transactional
  def findByReceivingUser(userId: String): JList[Transaction7] = {
    jdbcTemplate.query("select id, amount, reference, bank_slogan, receiving_user from transactions where receiving_user = ?",
      ROW_MAPPER, userId)
  }

  def findAll(): JList[Transaction7] = {
    jdbcTemplate.query("select id, amount, reference, bank_slogan, receiving_user from transactions", ROW_MAPPER)
  }

  @Transactional
  def create(amount: BigDecimal, reference: String, receivingUser: String): Transaction7 = {
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

    val transaction = Transaction7(amount, reference, bankSlogan, receivingUser)
    transaction.setId(uuid)
    transaction
  }
}

object TransactionService7 {
  final val ROW_MAPPER: RowMapper[Transaction7] = (resultSet: ResultSet, _: Int) => {
    val transaction = new Transaction7()
    transaction.id = resultSet.getObject("id").toString
    transaction.amount = resultSet.getBigDecimal("amount")
    transaction.reference = resultSet.getString("reference")
    transaction.bankSlogan = resultSet.getString("bank_slogan")
    transaction.receivingUser = resultSet.getString("receiving_user")
    transaction
  }
}