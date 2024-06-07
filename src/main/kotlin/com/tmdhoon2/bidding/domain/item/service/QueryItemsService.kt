package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.controller.dto.response.toItemsResponse
import com.tmdhoon2.bidding.domain.item.entity.BiddingStatus
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import com.tmdhoon2.bidding.domain.item.entity.repository.LikesRepository
import com.tmdhoon2.bidding.domain.item.entity.toUserLikesMap
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryItemsService(
    private val itemsRepository: ItemsRepository,
    private val likesRepository: LikesRepository,
) {
    @Transactional(readOnly = false)
    fun execute(userId: Long): ItemsResponse {
        val items = itemsRepository.findAll()
        val likes = likesRepository.findAllByUserId(userId).toUserLikesMap()
        return items.filter { it.biddingStatus == BiddingStatus.IN_BIDDING }.toItemsResponse(likes)
    }
}