package com.fma.spring.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fma.spring.BankApplicationLauncher2
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[BankApplicationLauncher2]))
@PropertySource(value = Array("classpath:/application.properties"))
class BankAppConfig2 {
  @Bean
  def objectMapper(): ObjectMapper = {
    new ObjectMapper().registerModule(DefaultScalaModule).registerModule(new JavaTimeModule())
  }
}
