package com.tmdhoon2.bidding.domain.user.service

import com.tmdhoon2.bidding.domain.user.controller.dto.request.CreateUserRequest
import com.tmdhoon2.bidding.domain.user.entity.User
import com.tmdhoon2.bidding.domain.user.entity.repository.UserRepository
import com.tmdhoon2.bidding.global.exception.ConflictException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateUserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    @Transactional
    fun execute(request: CreateUserRequest) {
        if (userRepository.existsByEmail(email = request.email)) {
            throw ConflictException(message = "user already exists")
        }
        userRepository.save(
            request.run {
                User(
                    email = email,
                    name = name,
                    password = passwordEncoder.encode(request.password),
                )
            },
        )
    }
}