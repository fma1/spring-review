package com.fma.spring.model

import scala.beans.BeanProperty

case class User(@BeanProperty var id: String, @BeanProperty var name: String)
