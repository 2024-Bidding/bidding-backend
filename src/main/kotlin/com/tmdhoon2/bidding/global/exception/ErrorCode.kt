package com.tmdhoon2.bidding.global.exception

enum class ErrorCode(
    val status: Int,
) {
    BAD_REQUEST(status = 400),
    NOT_FOUND(status = 404),
    UNAUTHORIZED(status = 401),
    FORBIDDEN(status = 403),
    CONFLICT(status = 409),
    INTERNAL_SERVER_ERROR(status = 500),
}