package com.fma.spring

import com.fma.spring.web.BankServlet1
import org.apache.catalina.startup.Tomcat

object BankApplicationLauncher1 {
  private val PORT = System.getProperty("server.port", "8080").toInt
  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(PORT)
    tomcat.getConnector

    val ctx = tomcat.addContext("", null)
    val servlet = Tomcat.addServlet(ctx, "BankServlet", new BankServlet1())
    servlet.addMapping("/")
    servlet.setLoadOnStartup(1)

    tomcat.start()
  }
}
