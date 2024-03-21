package com.tmdhoon2.bidding.domain.user.service

import com.gil.easyjwt.jwt.JwtTokenProvider
import com.tmdhoon2.bidding.domain.user.controller.dto.request.SignInRequest
import com.tmdhoon2.bidding.domain.user.controller.dto.response.SignInResponse
import com.tmdhoon2.bidding.domain.user.entity.repository.UserRepository
import com.tmdhoon2.bidding.global.exception.NotFoundException
import com.tmdhoon2.bidding.global.exception.UnauthorizedException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignInService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    fun execute(request: SignInRequest): SignInResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw NotFoundException(message = "user not found")

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw UnauthorizedException(message = "wrong password")
        }

        return SignInResponse(jwtTokenProvider.generateAccessToken(user.id.toString()))
    }
}