package com.tmdhoon2.bidding.domain.user.controller

import com.tmdhoon2.bidding.domain.user.controller.dto.request.CreateUserRequest
import com.tmdhoon2.bidding.domain.user.service.CreateUserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    private val createUserService: CreateUserService,
) {
    @PostMapping
    fun signUp(@RequestBody @Valid request: CreateUserRequest) {
        createUserService.execute(request)
    }
}