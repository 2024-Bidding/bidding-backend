package com.tmdhoon2.bidding.global.exception

class InternalServerErrorException : CustomException(
    errorCode = ErrorCode.INTERNAL_SERVER_ERROR,
    message = "Internal Server Error"
)

class NotFoundException(
    message: String
) : CustomException(
    errorCode = ErrorCode.NOT_FOUND,
    message = message
)

class ConflictException(
    message: String
) : CustomException(
    errorCode = ErrorCode.CONFLICT,
    message = message
)

class BadRequestException(
    message: String
) : CustomException(
    errorCode = ErrorCode.BAD_REQUEST,
    message = message
)

class UnauthorizedException(
    message: String
) : CustomException(
    errorCode = ErrorCode.UNAUTHORIZED,
    message = message
)

class ForbiddenException(
    message: String
) : CustomException(
    errorCode = ErrorCode.FORBIDDEN,
    message = message
)
