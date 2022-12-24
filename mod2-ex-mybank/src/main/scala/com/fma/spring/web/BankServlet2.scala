package com.fma.spring.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fma.spring.context.BankAppConfig2
import com.fma.spring.service.TransactionService2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext

import javax.servlet.ServletConfig
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class BankServlet2 extends HttpServlet {
  var transactionService: TransactionService2 = _
  var objectMapper: ObjectMapper = _

  override def init(config: ServletConfig): Unit = {
    val ctx = new AnnotationConfigApplicationContext(classOf[BankAppConfig2])
    transactionService = ctx.getBean(classOf[TransactionService2])
    objectMapper = ctx.getBean(classOf[ObjectMapper])
  }

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/transactions")) {
      val transactions = transactionService.findAll()
      val json = objectMapper.writeValueAsString(transactions)

      resp.setContentType("application/json; charset=UTF-8")
      resp.getWriter.write(json)
    }
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/transactions")) {
      val amount = BigDecimal(req.getParameter("amount"))
      val reference = req.getParameter("reference")
      val transaction = transactionService.create(amount, reference)

      val json = objectMapper.writeValueAsString(transaction)
      resp.setContentType("application/json; charset=UTF-8")
      resp.getWriter.write(json)
    }
  }
}
