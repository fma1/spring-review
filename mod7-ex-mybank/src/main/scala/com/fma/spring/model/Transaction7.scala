package com.fma.spring.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.{Id, Version}
import org.springframework.data.relational.core.mapping.Table

import java.time.ZonedDateTime
import java.util.UUID
import scala.beans.BeanProperty

@Table("transactions")
case class Transaction7(@BeanProperty var amount: java.math.BigDecimal,
                        @BeanProperty var reference: String,
                        @BeanProperty var bankSlogan: String,
                        @BeanProperty var receivingUser: String) {
  @BeanProperty
  @Id
  var id: String = UUID.randomUUID().toString

  @BeanProperty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mmZ")
  var timestamp: ZonedDateTime = ZonedDateTime.now()

  @BeanProperty
  @Version
  var version: Int = _

  def this() = this(null, null, null, null)
}
