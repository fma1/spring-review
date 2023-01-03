package com.fma.spring.dto

import javax.validation.constraints.{Min, NotBlank}
import scala.beans.BeanProperty

class TransactionDto7 {
  @BeanProperty
  @Min(0)
  var amount: BigDecimal = _

  @BeanProperty
  @NotBlank
  var reference: String = _

  @BeanProperty
  @NotBlank
  var receivingUser: String = _
}
