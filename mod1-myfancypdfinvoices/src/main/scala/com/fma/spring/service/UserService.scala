package com.fma.spring.service

import com.fma.spring.model.User

import java.util.UUID

class UserService {
  def findById(id: String): User = {
    val randomName = UUID.randomUUID().toString
    User(id, randomName)
  }
}
