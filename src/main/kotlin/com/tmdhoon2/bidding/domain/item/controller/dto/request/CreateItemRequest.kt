package com.tmdhoon2.bidding.domain.item.controller.dto.request

import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

data class CreateItemRequest(
    @field:NotNull
    @field:Size(max = 40)
    val name: String,

    @field:NotNull
    val endPrice: Long,

    @field:NotNull
    val startPrice: Long,

    @field:NotNull
    val imageUrl: List<String>,

    @field:NotNull
    val startTime: LocalDateTime,

    @field:NotNull
    val endTime: LocalDateTime,

    @field:NotNull
    val content: String,
)