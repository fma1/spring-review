package com.fma.spring.dto

import javax.validation.constraints.{Min, NotBlank}
import scala.beans.BeanProperty

class TransactionDto {
  @BeanProperty
  @Min(0)
  var amount: BigDecimal = _

  @BeanProperty
  @NotBlank
  var reference: String = _
}
