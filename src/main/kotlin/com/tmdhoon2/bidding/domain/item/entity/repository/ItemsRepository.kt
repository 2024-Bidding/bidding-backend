package com.tmdhoon2.bidding.domain.item.entity.repository

import com.tmdhoon2.bidding.domain.item.entity.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemsRepository : JpaRepository<Item, Long> {
    fun findAllByUserId(userId: Long): List<Item>
}