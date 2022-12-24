package com.fma.spring.service

import com.fma.spring.model.Transaction1

import java.util.{List => JList}
import java.util.concurrent.CopyOnWriteArrayList

class TransactionService1 {
  private val transactions = new CopyOnWriteArrayList[Transaction1]()

  def findAll(): JList[Transaction1] = {
    transactions
  }

  def create(amount: BigDecimal, reference: String): Transaction1 = {
    val transaction = Transaction1(amount, reference)
    transactions.add(transaction)
    transaction
  }
}
