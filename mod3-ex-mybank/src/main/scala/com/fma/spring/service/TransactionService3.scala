package com.fma.spring.service

import com.fma.spring.model.Transaction3
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.util.{List => JList}
import java.util.concurrent.CopyOnWriteArrayList

@Component
class TransactionService3(@Value("${bank.slogan}") bankSlogan: String) {
  private val transactions = new CopyOnWriteArrayList[Transaction3]()

  def findAll(): JList[Transaction3] = {
    transactions
  }

  def create(amount: BigDecimal, reference: String): Transaction3 = {
    val transaction = Transaction3(amount, reference, bankSlogan)
    transactions.add(transaction)
    transaction
  }
}
