package com.tmdhoon2.bidding.domain.item.controller.dto.response

import com.tmdhoon2.bidding.domain.item.entity.BiddingStatus
import com.tmdhoon2.bidding.domain.item.entity.Item

data class ItemDetailsResponse(
    val name: String,
    val currentPrice: Long,
    val maxPrice: Long,
    val imageUrls: List<String>,
    val startTime: String,
    val endTime: String,
    val biddingStatus: BiddingStatus,
    val userName: String,
    val content: String,
)

fun Item.toItemDetailsResponse(imageUrls: List<String>) = ItemDetailsResponse(
    name = this.name,
    currentPrice = this.currentPrice,
    maxPrice = this.endPrice,
    imageUrls = imageUrls,
    startTime = this.startTime.toString().replace('T', ' '),
    endTime = this.endTime.toString().replace('T', ' '),
    biddingStatus = biddingStatus,
    userName = user.name,
    content = this.content,
)