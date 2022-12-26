package com.fma.spring.service

import com.fma.spring.model.Transaction4
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

import java.util
import java.util.{ArrayList, List => JList}
import java.util.concurrent.CopyOnWriteArrayList
import javax.annotation.PostConstruct
import scala.jdk.CollectionConverters._

@Component
class TransactionService4(@Value("${bank.slogan}") bankSlogan: String) {
  @Autowired
  var env: Environment = _

  private val transactions = new CopyOnWriteArrayList[Transaction4]()

  @PostConstruct
  def postConstruct(): Unit = {
    if (env.getActiveProfiles.contains("dev")) {
      System.out.println("DEV profile active, adding dummy data.")
      create(50.0, "ref1", "user1")
      create(60.0, "ref2", "user2")
    }
  }

  def findByReceivingUser(userId: String): JList[Transaction4] = {
    val filteredCollection = transactions.asScala.filter(_.receivingUser == userId).asJavaCollection
    val aryList = new util.ArrayList[Transaction4](filteredCollection)
    aryList
  }

  def findAll(): JList[Transaction4] = {
    transactions
  }

  def create(amount: BigDecimal, reference: String, receivingUser: String): Transaction4 = {
    val transaction = Transaction4(amount, reference, bankSlogan, receivingUser)
    transactions.add(transaction)
    transaction
  }
}
