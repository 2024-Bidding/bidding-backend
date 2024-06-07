package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.controller.dto.response.toItemsResponse
import com.tmdhoon2.bidding.domain.item.entity.BiddingStatus
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import com.tmdhoon2.bidding.domain.item.entity.repository.LikesRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QueryItemsService(
    private val itemsRepository: ItemsRepository,
    private val likesRepository: LikesRepository,
) {
    @Transactional
    fun execute(userId: Long): ItemsResponse {
        val items = itemsRepository.findAll()
        val likes = likesRepository.findAllByUserId(userId).map { it.item.id to true }.toMap()
        return items.filter { it.biddingStatus == BiddingStatus.IN_BIDDING }.toItemsResponse(likes)
    }
}