package com.fma.spring.service

import com.fma.spring.model.Transaction2

import java.util.{List => JList}
import java.util.concurrent.CopyOnWriteArrayList

class TransactionService2 {
  private val transactions = new CopyOnWriteArrayList[Transaction2]()

  def findAll(): JList[Transaction2] = {
    transactions
  }

  def create(amount: BigDecimal, reference: String): Transaction2 = {
    val transaction = Transaction2(amount, reference)
    transactions.add(transaction)
    transaction
  }
}
