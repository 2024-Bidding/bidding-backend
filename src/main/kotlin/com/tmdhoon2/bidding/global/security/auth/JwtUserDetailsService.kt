package com.tmdhoon2.bidding.global.security.auth

import com.gil.easyjwt.user.JwtUser
import com.gil.easyjwt.user.QueryJwtUserService
import com.tmdhoon2.bidding.domain.user.entity.repository.UserRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUserDetailsService(
    private val userRepository: UserRepository,
) : QueryJwtUserService {
    override fun execute(email: String): Optional<JwtUser> {
        val user = userRepository.findByEmail(email)
        return if (user == null) {
            Optional.empty()
        } else {
            Optional.of(user as JwtUser)
        }
    }
}