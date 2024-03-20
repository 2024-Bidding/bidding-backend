package com.tmdhoon2.bidding.global.exception

open class CustomException(
    val errorCode: ErrorCode,
    override val message: String,
) : RuntimeException(message) {

}