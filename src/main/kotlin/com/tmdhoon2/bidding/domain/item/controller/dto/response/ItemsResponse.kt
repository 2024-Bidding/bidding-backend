package com.tmdhoon2.bidding.domain.item.controller.dto.response

import com.tmdhoon2.bidding.domain.item.entity.Item

data class ItemsResponse(
    val items: List<Item>,
)
