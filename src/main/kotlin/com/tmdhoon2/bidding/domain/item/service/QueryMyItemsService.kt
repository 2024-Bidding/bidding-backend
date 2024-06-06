package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QueryMyItemsService(
    private val itemsRepository: ItemsRepository,
) {
    @Transactional
    fun execute(userId: Long): ItemsResponse {
        val items = itemsRepository.findAllByUserId(userId).map {
            ItemsResponse.ItemResponse(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                endTime = it.endTime,
                currentPrice = it.currentPrice,
                userName = it.user.name,
                userProfileUrl = it.user.profileImageUrl,
                isLiked = false,
            )
        }

        return ItemsResponse(items = items)
    }
}