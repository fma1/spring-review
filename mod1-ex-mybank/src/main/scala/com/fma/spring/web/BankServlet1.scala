package com.fma.spring.web

import com.fma.spring.context.BankApplicationContext1._
import com.fma.spring.model.Transaction1

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class BankServlet1 extends HttpServlet {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/transactions")) {
      val transactions = transactionService.findAll()
      val json = objectMapper.writeValueAsString(transactions)
      resp.getWriter.write(json)
    }
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/transactions")) {
      val amount = BigDecimal(req.getParameter("amount"))
      val reference = req.getParameter("reference")
      val transaction = transactionService.create(amount, reference)
      val json = objectMapper.writeValueAsString(transaction)
      resp.getWriter.write(json)
    }
  }
}
