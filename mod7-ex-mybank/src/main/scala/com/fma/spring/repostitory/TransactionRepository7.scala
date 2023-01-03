package com.fma.spring.repostitory

import com.fma.spring.model.Transaction7
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import java.lang.{Iterable => JIterable}

@Repository
trait TransactionRepository7 extends CrudRepository[Transaction7, String] {
  @Query("SELECT id, amount, reference, bank_slogan, receiving_user from \"transactions\" where receiving_user = :userId")
  def findByUser(@Param("userId") userId: String): JIterable[Transaction7]
}
