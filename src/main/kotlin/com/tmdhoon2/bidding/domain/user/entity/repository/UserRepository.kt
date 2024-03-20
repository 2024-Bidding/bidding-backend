package com.tmdhoon2.bidding.domain.user.entity.repository

import com.tmdhoon2.bidding.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean
}