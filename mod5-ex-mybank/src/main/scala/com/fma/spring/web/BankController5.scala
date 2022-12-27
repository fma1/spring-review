package com.fma.spring.web

import com.fma.spring.dto.TransactionDto5
import com.fma.spring.model.Transaction5
import com.fma.spring.service.TransactionService5
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RestController}

import java.util.{List => JList}
import javax.validation.Valid

@RestController
class BankController5(transactionService: TransactionService5) {
  @GetMapping(Array("/transactions"))
  def transactions(): JList[Transaction5] = {
    transactionService.findAll()
  }

  @PostMapping(Array("/transactions"))
  def createTransaction(@Valid @RequestBody transactionDto: TransactionDto5): Transaction5 = {
    transactionService.create(transactionDto.amount, transactionDto.reference, transactionDto.receivingUser)
  }
}
