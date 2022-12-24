package com.fma.spring.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fma.spring.service.TransactionService2
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class BankAppConfig2 {
  @Bean
  def transactionService(): TransactionService2 = {
    new TransactionService2()
  }

  @Bean
  def objectMapper(): ObjectMapper = {
    new ObjectMapper().registerModule(DefaultScalaModule).registerModule(new JavaTimeModule())
  }
}
