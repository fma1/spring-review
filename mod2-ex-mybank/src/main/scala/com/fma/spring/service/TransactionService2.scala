package com.fma.spring.service

import com.fma.spring.model.Transaction2
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.util.{List => JList}
import java.util.concurrent.CopyOnWriteArrayList

@Component
class TransactionService2(@Value("${bank.slogan}") bankSlogan: String) {
  private val transactions = new CopyOnWriteArrayList[Transaction2]()

  def findAll(): JList[Transaction2] = {
    transactions
  }

  def create(amount: BigDecimal, reference: String): Transaction2 = {
    val transaction = Transaction2(amount, reference, bankSlogan)
    transactions.add(transaction)
    transaction
  }
}
