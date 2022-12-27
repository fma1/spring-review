package com.fma.spring.web

import com.fma.spring.dto.TransactionDto5
import com.fma.spring.service.TransactionService5
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.{GetMapping, ModelAttribute, PathVariable, PostMapping}

import javax.validation.Valid

@Controller
class WebController5(transactionService: TransactionService5) {
  private var currentUserId: String = _

  @GetMapping(Array("/account/{userId}"))
  def account(@PathVariable userId: String, model: Model): String = {
    model.addAttribute("transactionLst", transactionService.findByReceivingUser(userId))
    model.addAttribute("addTxForm", new TransactionDto5())
    currentUserId = userId
    "account.html"
  }

  @PostMapping(Array("/addTransaction"))
  def addTransaction(@ModelAttribute @Valid txForm: TransactionDto5, bindingResult: BindingResult, model: Model): String = {
    if (bindingResult.hasErrors) {
      model.addAttribute("invalidInput", "true")
    } else {
      transactionService.create(txForm.amount, txForm.reference, txForm.receivingUser)
    }

    s"redirect:/account/$currentUserId"
  }
}
