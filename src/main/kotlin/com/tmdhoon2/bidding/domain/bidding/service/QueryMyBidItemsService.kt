package com.tmdhoon2.bidding.domain.bidding.service

import com.tmdhoon2.bidding.domain.bidding.entity.repository.BidRepository
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.collections.map

@Service
class QueryMyBidItemsService(
    private val bidRepository: BidRepository,
) {
    @Transactional(readOnly = true)
    fun execute(userId: Long): ItemsResponse {
        val items = bidRepository.findAllByUserId(userId).map {
            with(it.item) {
                ItemsResponse.ItemResponse(
                    id = id,
                    name = name,
                    imageUrl = imageUrl,
                    endTime = endTime,
                    currentPrice = currentPrice,
                    userName = user.name,
                    userProfileUrl = user.profileImageUrl,
                    isLiked = isLiked,
                )
            }
        }

        return ItemsResponse(items = items)
    }
}