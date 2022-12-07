package com.fma.spring.service

import com.fma.spring.model.Invoice

import java.util.concurrent.CopyOnWriteArrayList

class InvoiceService(userService: UserService) {
  private val invoices = new CopyOnWriteArrayList[Invoice]()

  def findAll(): CopyOnWriteArrayList[Invoice] = {
    invoices
  }

  def create(userId: String, amount: Int): Invoice = {
    val user = userService.findById(userId)
    if (user == null) {
      throw new IllegalStateException()
    }

    val invoice = Invoice(userId, amount, "http://www.somewebsite.com/directory/sample.pdf")
    invoices.add(invoice)
    invoice
  }
}
