package com.fma.spring.service

import com.fma.spring.model.Transaction7
import com.fma.spring.repostitory.TransactionRepository7
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

import java.lang.{Iterable => JIterable}
import scala.beans.BeanProperty

@Component
class TransactionService7(transactionRepository: TransactionRepository7) {
  @BeanProperty
  @Value("${bank.slogan}")
  var bankSlogan: String = _

  @Transactional
  def findByReceivingUser(userId: String): JIterable[Transaction7] = {
    transactionRepository.findByUser(userId)
  }

  def findAll(): JIterable[Transaction7] = {
    transactionRepository.findAll()
  }

  @Transactional
  def create(amount: java.math.BigDecimal, reference: String, receivingUser: String): Transaction7 = {
    transactionRepository.save(Transaction7(amount, reference, bankSlogan, receivingUser))
  }
}