package com.fma.spring.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fma.spring.BankApplicationLauncher4
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, WebMvcConfigurer}
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[BankApplicationLauncher4]))
@PropertySource(value = Array("classpath:/application.properties"))
@EnableWebMvc
class BankAppConfig4 extends WebMvcConfigurer {
  @Bean
  def objectMapper(): ObjectMapper = {
    new ObjectMapper().registerModule(DefaultScalaModule).registerModule(new JavaTimeModule())
  }

  @Bean
  def viewResolver(): ThymeleafViewResolver = {
    val viewResolver = new ThymeleafViewResolver()
    viewResolver.setTemplateEngine(templateEngine())
    viewResolver.setOrder(1)
    viewResolver.setViewNames(Array("*.html", "*.xhtml"))
    viewResolver
  }

  @Bean
  def templateEngine(): SpringTemplateEngine = {
    val templateEngine = new SpringTemplateEngine()
    templateEngine.setTemplateResolver(templateResolver())
    templateEngine
  }

  @Bean
  def templateResolver(): SpringResourceTemplateResolver = {
    val templateResolver = new SpringResourceTemplateResolver()
    templateResolver.setPrefix("classpath:/templates/")
    // true in production environment
    templateResolver.setCacheable(false)
    templateResolver
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
