package com.fma.spring.model

import com.fasterxml.jackson.annotation.JsonProperty

import scala.beans.BeanProperty

case class Invoice(@BeanProperty @JsonProperty("user_id") var userId: String,
                   @BeanProperty var amount: Int,
                   @BeanProperty @JsonProperty("pdf_url") var pdfUrl: String)
