package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.controller.dto.response.toItemsResponse
import com.tmdhoon2.bidding.domain.item.entity.repository.LikesRepository
import com.tmdhoon2.bidding.domain.item.entity.toUserLikesMap
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyLikeItemsService(
    private val likesRepository: LikesRepository,
) {
    @Transactional(readOnly = true)
    fun execute(userId: Long): ItemsResponse {
        val likes = likesRepository.findAllByUserId(userId = userId)
        return likes.map { it.item }.toItemsResponse(likes.toUserLikesMap())
    }
}