package com.tmdhoon2.bidding.domain.user.controller.dto.request

import org.jetbrains.annotations.NotNull

data class SignInRequest(
    @field:NotNull
    val email: String,

    @field:NotNull
    val password: String,
)