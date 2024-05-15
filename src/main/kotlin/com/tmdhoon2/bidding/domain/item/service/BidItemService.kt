package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.controller.dto.request.BidItemRequest
import com.tmdhoon2.bidding.domain.item.entity.BiddingStatus
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BidItemService(
    private val itemsRepository: ItemsRepository,
) {
    companion object {
        const val SUCCESS = 200
        const val NO_CONTENT = 204
        const val BAD_REQUEST = 400
        const val NOT_FOUND = 404
    }

    @Transactional
    operator fun invoke(
        itemId: Long,
        userId: Long,
        request: BidItemRequest,
    ): Int {
        val item = itemsRepository.findByIdOrNull(itemId) ?: return NOT_FOUND

        val updateItem: (Boolean) -> Unit = { isSuccessfulBid ->
            val (userId, biddingStatus) = if (isSuccessfulBid) userId to BiddingStatus.AFTER_BIDDING
            else null to item.biddingStatus
            itemsRepository.save(
                item.copy(
                    currentPrice = request.price,
                    userId = userId,
                    biddingStatus = biddingStatus
                )
            )
        }

        return when (request.price) {
            !in item.currentPrice + 1..item.endPrice -> {
                BAD_REQUEST
            }

            item.endPrice -> {
                updateItem(true)
                NO_CONTENT
            }

            else -> {
                updateItem(false)
                SUCCESS
            }
        }
    }
}


