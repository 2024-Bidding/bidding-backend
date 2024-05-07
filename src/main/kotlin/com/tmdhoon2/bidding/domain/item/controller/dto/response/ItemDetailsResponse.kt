package com.tmdhoon2.bidding.domain.item.controller.dto.response

import com.tmdhoon2.bidding.domain.item.entity.BiddingStatus

data class ItemDetailsResponse(
    val name: String,
    val currentPrice: Long,
    val maxPrice: Long,
    val imageUrls: List<String>,
    val startTime: String,
    val endTime: String,
    val biddingStatus: BiddingStatus,
)