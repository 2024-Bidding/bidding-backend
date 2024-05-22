package com.tmdhoon2.bidding.domain.item.entity

import com.tmdhoon2.bidding.domain.user.entity.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

@Entity
data class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    val name: String,

    @field:NotNull
    @Column(columnDefinition = "BIGINT")
    val startPrice: Long,

    @field:NotNull
    @Column(columnDefinition = "BIGINT")
    val endPrice: Long,

    @field:NotNull
    @Column(columnDefinition = "DATETIME(6)")
    val startTime: LocalDateTime,

    @Column(columnDefinition = "DATETIME(6)")
    val endTime: LocalDateTime,

    @field:NotNull
    @Column(columnDefinition = "BIGINT")
    val currentPrice: Long,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    val biddingStatus: BiddingStatus,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(255)")
    val content: String,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(255)")
    val imageUrl: String,
)
