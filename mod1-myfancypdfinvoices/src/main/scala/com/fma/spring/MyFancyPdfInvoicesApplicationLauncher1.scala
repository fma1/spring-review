package com.fma.spring

import com.fma.spring.web.MyFancyPdfInvoicesServlet1
import org.apache.catalina.startup.Tomcat

object MyFancyPdfInvoicesApplicationLauncher1 {
  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(8080)
    tomcat.getConnector

    val ctx = tomcat.addContext("", null)
    val servlet = Tomcat.addServlet(ctx, "MyServlet", new MyFancyPdfInvoicesServlet1())
    servlet.setLoadOnStartup(1)
    servlet.addMapping("/*")

    tomcat.start()
  }
}
