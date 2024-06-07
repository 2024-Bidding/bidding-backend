package com.tmdhoon2.bidding.domain.item.controller.dto.response

import com.tmdhoon2.bidding.domain.item.entity.Item
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
        val isLiked: Boolean,
    )
}

fun List<Item>.toItemsResponse(likes: Map<Long, Boolean>) = ItemsResponse(
    items = map {
        val isLiked = likes.getOrDefault(
            key = it.id,
            defaultValue = false,
        )
        it.toItemResponse(isLiked)
    }
)

fun Item.toItemResponse(isLiked: Boolean) = ItemsResponse.ItemResponse(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
    endTime = this.endTime,
    currentPrice = this.currentPrice,
    userName = this.user.name,
    userProfileUrl = this.user.profileImageUrl,
    isLiked = isLiked,
)
