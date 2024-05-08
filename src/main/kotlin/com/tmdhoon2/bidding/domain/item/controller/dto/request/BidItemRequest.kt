package com.tmdhoon2.bidding.domain.item.controller.dto.request

import org.jetbrains.annotations.NotNull

data class BidItemRequest(
    @field:NotNull
    val price: Long,
)
