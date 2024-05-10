package com.tmdhoon2.bidding.domain.user.controller.dto.response

import com.tmdhoon2.bidding.domain.user.entity.User

data class UserInformationResponse(
    val email: String,
    val name: String,
    val profileImageUrl: String,
)

fun User.toResponse(): UserInformationResponse {
    return UserInformationResponse(
        email = this.email,
        name = this.name,
        profileImageUrl = this.profileImageUrl,
    )
}
