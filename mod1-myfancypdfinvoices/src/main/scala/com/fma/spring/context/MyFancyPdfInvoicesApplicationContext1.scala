package com.fma.spring.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fma.spring.service._

object MyFancyPdfInvoicesApplicationContext1 {
  val userService = new UserService()
  val invoiceService = new InvoiceService(userService)
  val objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)
}
