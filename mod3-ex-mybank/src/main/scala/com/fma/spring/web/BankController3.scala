package com.fma.spring.web

import com.fma.spring.dto.TransactionDto3
import com.fma.spring.model.Transaction3
import com.fma.spring.service.TransactionService3
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RestController}

import java.util.{List => JList}
import javax.validation.Valid

@RestController
class BankController3(transactionService: TransactionService3) {
  @GetMapping(Array("/transactions"))
  def transactions(): JList[Transaction3] = {
    transactionService.findAll()
  }

  @PostMapping(Array("/transactions"))
  def createTransaction(@Valid @RequestBody transactionDto: TransactionDto3): Transaction3 = {
    transactionService.create(transactionDto.amount, transactionDto.reference)
  }
}
