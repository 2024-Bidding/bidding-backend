package com.tmdhoon2.bidding.domain.user.controller.dto.request

import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

data class CreateUserRequest(
    @field:NotNull
    @field:Size(max = 30)
    val email: String,

    @field:NotNull
    @field:Size(max = 30)
    val password: String,

    @field:NotNull
    val name: String,

    val profileImageUrl: String?,
)