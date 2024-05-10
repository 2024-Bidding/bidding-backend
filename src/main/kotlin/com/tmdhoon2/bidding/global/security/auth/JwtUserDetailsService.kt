package com.tmdhoon2.bidding.global.security.auth

import com.gil.easyjwt.user.JwtUser
import com.gil.easyjwt.user.QueryJwtUserService
import com.tmdhoon2.bidding.domain.user.entity.repository.UserRepository
import com.tmdhoon2.bidding.global.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUserDetailsService(
    private val userRepository: UserRepository,
) : QueryJwtUserService {
    override fun execute(id: String): Optional<JwtUser> {
        val user = userRepository.findByIdOrNull(id.toLong()) ?: throw NotFoundException("User Not Found")
        return Optional.of(user as JwtUser)
    }
}