package com.fma.spring.web

import com.fma.spring.dto.TransactionDto7
import com.fma.spring.model.Transaction7
import com.fma.spring.service.TransactionService7
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RequestParam, RestController}

import java.lang.{Iterable => JIterable}
import javax.validation.Valid

@RestController
class TransactionController7(transactionService: TransactionService7) {
  @GetMapping(Array("/transactions"))
  def transactions(): JIterable[Transaction7] = {
    transactionService.findAll()
  }

  @GetMapping(Array("/transactionsByUserId"))
  def transactionsByUserId(@RequestParam userId: String): JIterable[Transaction7] = {
    transactionService.findByReceivingUser(userId)
  }

  @PostMapping(Array("/transactions"))
  def createTransaction(@Valid @RequestBody transactionDto: TransactionDto7): Transaction7 = {
    transactionService.create(transactionDto.amount, transactionDto.reference, transactionDto.receivingUser)
  }
}
