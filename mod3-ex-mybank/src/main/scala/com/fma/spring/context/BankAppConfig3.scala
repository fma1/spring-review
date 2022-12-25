package com.fma.spring.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fma.spring.BankApplicationLauncher3
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, WebMvcConfigurer}

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[BankApplicationLauncher3]))
@PropertySource(value = Array("classpath:/application.properties"))
@EnableWebMvc
class BankAppConfig3 extends WebMvcConfigurer {
  @Bean
  def objectMapper(): ObjectMapper = {
    new ObjectMapper().registerModule(DefaultScalaModule).registerModule(new JavaTimeModule())
  }

  override def addFormatters(registry: FormatterRegistry): Unit = {
    val stringToBigDecimalConverter = new Converter[String, BigDecimal] {
      override def convert(source: String): BigDecimal = {
        BigDecimal(source)
      }
    }

    registry.addConverter(stringToBigDecimalConverter)
  }
}
