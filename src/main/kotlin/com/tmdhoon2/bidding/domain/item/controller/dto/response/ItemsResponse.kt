package com.tmdhoon2.bidding.domain.item.controller.dto.response

import java.time.LocalDateTime

data class ItemsResponse(
    val items: List<ItemResponse>,
) {
    data class ItemResponse(
        val id: Long,
        val name: String,
        val imageUrl: String,
        val endTime: LocalDateTime,
        val currentPrice: Long,
        val userName: String,
        val userProfileUrl: String,
    )
}
