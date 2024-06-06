package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryBiddingHistoryService(
    private val itemsRepository: ItemsRepository,
) {
    @Transactional(readOnly = true)
    fun execute(userId: Long): ItemsResponse {
        val items = itemsRepository.findAllByUserId(userId)
        return ItemsResponse(
            items = items.map { item ->
                with(item) {
                    ItemsResponse.ItemResponse(
                        id = id,
                        name = name,
                        imageUrl = imageUrl,
                        endTime = endTime,
                        currentPrice = currentPrice,
                        userName = user.name,
                        userProfileUrl = user.profileImageUrl,
                    )
                }
            }
        )
    }
}