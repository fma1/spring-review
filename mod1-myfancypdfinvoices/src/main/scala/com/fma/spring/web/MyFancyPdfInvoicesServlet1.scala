package com.fma.spring.web

import com.fma.spring.context.MyFancyPdfInvoicesApplicationContext1._

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class MyFancyPdfInvoicesServlet1 extends HttpServlet {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/")) {
      val HTML =
        """<html>
          |<head>
          |<title>Index</title>
          |</head>
          |<body>
          |<h1>Hello World!</h1>
          |</body>
          |</html>
          |""".stripMargin
      resp.setContentType("text/html; charset=UTF-8")
      resp.getWriter.print(HTML)
    } else {
      val invoices = invoiceService.findAll()
      resp.getWriter.print(objectMapper.writeValueAsString(invoices))
    }
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/invoices")) {
      val userId = req.getParameter("user_id")
      val amount = req.getParameter("amount").toInt

      val invoice = invoiceService.create(userId, amount)

      resp.setContentType("application/json; charset=UTF-8")
      resp.getWriter.print(objectMapper.writeValueAsString(invoice))
    }
  }
}
