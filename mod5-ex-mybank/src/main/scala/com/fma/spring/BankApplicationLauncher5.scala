package com.fma.spring

import com.fma.spring.context.BankAppConfig5
import org.apache.catalina.startup.Tomcat
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import javax.servlet.ServletContext

object BankApplicationLauncher5 {
  private val PORT = System.getProperty("server.port", "8080").toInt
  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(PORT)
    tomcat.getConnector

    val tomcatCtx = tomcat.addContext("", null)
    val appCtx = createApplicationContext(tomcatCtx.getServletContext)
    val servlet = Tomcat.addServlet(tomcatCtx, "DispatcherServlet", new DispatcherServlet(appCtx))
    servlet.addMapping("/")
    servlet.setLoadOnStartup(1)

    tomcat.start()
  }

  private def createApplicationContext(servlet: ServletContext): WebApplicationContext = {
    val ctx = new AnnotationConfigWebApplicationContext()
    ctx.register(classOf[BankAppConfig5])
    ctx.setServletContext(servlet)
    ctx.refresh()
    ctx.registerShutdownHook()
    ctx
  }
}

class BankApplicationLauncher5
