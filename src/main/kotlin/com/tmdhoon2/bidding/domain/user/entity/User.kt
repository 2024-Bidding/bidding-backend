package com.tmdhoon2.bidding.domain.user.entity

import com.gil.easyjwt.user.JwtUser
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull
import org.springframework.security.core.GrantedAuthority

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    val email: String,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    val name: String,

    @field:NotNull
    @Column(columnDefinition = "CHAR(60)")
    val password: String,

    @Column(columnDefinition = "VARCHAR(255)")
    val profileImageUrl: String,
) : JwtUser(email) {

    override fun getUsername(): String = email

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf();
}
