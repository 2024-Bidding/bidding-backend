package com.tmdhoon2.bidding.domain.user.controller

import com.tmdhoon2.bidding.domain.user.controller.dto.request.CreateUserRequest
import com.tmdhoon2.bidding.domain.user.controller.dto.request.SignInRequest
import com.tmdhoon2.bidding.domain.user.controller.dto.response.SignInResponse
import com.tmdhoon2.bidding.domain.user.service.CreateUserService
import com.tmdhoon2.bidding.domain.user.service.SignInService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    private val createUserService: CreateUserService,
    private val signInService: SignInService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signUp")
    fun signUp(@RequestBody @Valid request: CreateUserRequest) {
        createUserService.execute(request)
    }

    @PostMapping("/signIn")
    fun signIn(@RequestBody @Valid request: SignInRequest): SignInResponse {
        return signInService.execute(request)
    }
}