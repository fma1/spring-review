package com.fma.spring

import com.fma.spring.web.BankServlet2
import org.apache.catalina.startup.Tomcat

object BankApplicationLauncher2 {
  private val PORT = System.getProperty("server.port", "8080").toInt
  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(PORT)
    tomcat.getConnector

    val ctx = tomcat.addContext("", null)
    val servlet = Tomcat.addServlet(ctx, "BankServlet", new BankServlet2())
    servlet.addMapping("/")
    servlet.setLoadOnStartup(1)

    tomcat.start()
  }
}

class BankApplicationLauncher2