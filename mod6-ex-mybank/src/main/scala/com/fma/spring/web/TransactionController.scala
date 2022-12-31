package com.fma.spring.web

import com.fma.spring.dto.TransactionDto6
import com.fma.spring.model.Transaction6
import com.fma.spring.service.TransactionService6
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RestController}

import java.util.{List => JList}
import javax.validation.Valid

@RestController
class TransactionController(transactionService: TransactionService6) {

  @GetMapping(Array("/transactions"))
  def transaction(): JList[Transaction6] = {
    transactionService.findAll()
  }

  @PostMapping(Array("/transactions"))
  def createTransaction(@Valid @RequestBody transactionDto: TransactionDto6): Transaction6 = {
    transactionService.create(transactionDto.amount, transactionDto.reference, transactionDto.receivingUser)
  }
}
