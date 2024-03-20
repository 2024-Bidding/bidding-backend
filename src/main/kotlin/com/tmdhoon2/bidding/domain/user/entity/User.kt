package com.tmdhoon2.bidding.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    val email: String,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    val name: String,

    @field:NotNull
    @Column(columnDefinition = "CHAR(60)")
    val password: String,
)
