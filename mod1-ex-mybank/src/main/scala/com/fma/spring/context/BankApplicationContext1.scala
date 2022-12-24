package com.fma.spring.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fma.spring.service._

object BankApplicationContext1 {
  val transactionService: TransactionService1 = new TransactionService1()
  val objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule).registerModule(new JavaTimeModule())
}
