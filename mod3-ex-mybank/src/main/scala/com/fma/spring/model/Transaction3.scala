package com.fma.spring.model

import com.fasterxml.jackson.annotation.JsonFormat

import java.time.ZonedDateTime
import java.util.UUID
import scala.beans.BeanProperty

case class Transaction3(@BeanProperty var amount: BigDecimal, @BeanProperty var reference: String, @BeanProperty var bankSlogan: String) {
  @BeanProperty
  var id: String = UUID.randomUUID().toString

  @BeanProperty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mmZ")
  var timestamp: ZonedDateTime = ZonedDateTime.now()
}
