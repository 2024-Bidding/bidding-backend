package com.tmdhoon2.bidding.domain.item.entity

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

@Entity
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    val name: String,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(10)")
    val startPrice: String,

    @field:NotNull
    @Column(columnDefinition = "BIGINT")
    val endPrice: Long,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(255)")
    val imageUrl: String,

    @field:NotNull
    @Column(columnDefinition = "DATETIME(6)")
    val startTime: LocalDateTime,

    @Column(columnDefinition = "DATETIME(6)")
    val endTime: LocalDateTime,

    @field:NotNull
    @Column(columnDefinition = "BIGINT")
    val currentPrice: Long,
)