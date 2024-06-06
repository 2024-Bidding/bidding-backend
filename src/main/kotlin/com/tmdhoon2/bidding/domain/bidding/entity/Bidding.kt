package com.tmdhoon2.bidding.domain.bidding.entity

import com.tmdhoon2.bidding.domain.item.entity.Item
import com.tmdhoon2.bidding.domain.user.entity.User
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
class Bidding(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotNull
    @Column(columnDefinition = "BIGINT")
    val price: Long,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: Item,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
)