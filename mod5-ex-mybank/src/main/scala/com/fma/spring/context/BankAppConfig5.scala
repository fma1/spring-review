package com.fma.spring.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fma.spring.BankApplicationLauncher5
import org.h2.jdbcx.JdbcDataSource
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.{EnableWebMvc, WebMvcConfigurer}
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver

import javax.sql.DataSource

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[BankApplicationLauncher5]))
@PropertySource(value = Array("classpath:/application.properties"))
@EnableWebMvc
@EnableTransactionManagement
class BankAppConfig5 extends WebMvcConfigurer {
  @Bean
  def platformTransactionManager: TransactionManager = {
    new DataSourceTransactionManager(dataSource())
  }

  @Bean
  def dataSource(): DataSource = {
    val ds = new JdbcDataSource()
    ds.setURL("jdbc:h2:~/BankH2Database;INIT=RUNSCRIPT FROM 'classpath:schema.sql';IGNORECASE=TRUE")
    ds.setUser("sa")
    ds.setPassword("sa")
    ds
  }

  @Bean
  def jdbcTemplate(): JdbcTemplate = {
    new JdbcTemplate(dataSource())
  }

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
