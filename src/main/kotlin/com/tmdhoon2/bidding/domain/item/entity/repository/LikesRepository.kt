package com.tmdhoon2.bidding.domain.item.entity.repository

import com.tmdhoon2.bidding.domain.item.entity.Likes
import org.springframework.data.jpa.repository.JpaRepository

interface LikesRepository : JpaRepository<Likes, Long> {
    fun findAllByUserId(userId: Long): List<Likes>
}