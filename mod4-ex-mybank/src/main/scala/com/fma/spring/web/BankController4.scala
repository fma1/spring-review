package com.fma.spring.web

import com.fma.spring.dto.TransactionDto4
import com.fma.spring.model.Transaction4
import com.fma.spring.service.TransactionService4
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RestController}

import java.util.{List => JList}
import javax.validation.Valid

@RestController
class BankController4(transactionService: TransactionService4) {
  @GetMapping(Array("/transactions"))
  def transactions(): JList[Transaction4] = {
    transactionService.findAll()
  }

  @PostMapping(Array("/transactions"))
  def createTransaction(@Valid @RequestBody transactionDto: TransactionDto4): Transaction4 = {
    transactionService.create(transactionDto.amount, transactionDto.reference, transactionDto.receivingUser)
  }
}
