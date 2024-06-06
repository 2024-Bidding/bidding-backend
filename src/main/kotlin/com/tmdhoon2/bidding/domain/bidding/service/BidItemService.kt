package com.tmdhoon2.bidding.domain.bidding.service

import com.tmdhoon2.bidding.domain.bidding.controller.dto.request.BidItemRequest
import com.tmdhoon2.bidding.domain.bidding.entity.Bidding
import com.tmdhoon2.bidding.domain.bidding.entity.repository.BidRepository
import com.tmdhoon2.bidding.domain.item.entity.BiddingStatus
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import com.tmdhoon2.bidding.domain.user.entity.User
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BidItemService(
    private val itemsRepository: ItemsRepository,
    private val bidRepository: BidRepository,
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
        user: User,
        request: BidItemRequest,
    ): Int {
        val item = itemsRepository.findByIdOrNull(itemId) ?: return NOT_FOUND

        return when (request.price) {
            !in item.currentPrice + 1..item.endPrice -> BAD_REQUEST
            item.endPrice -> {
                itemsRepository.save(
                    item.copy(
                        currentPrice = request.price,
                        biddingStatus = BiddingStatus.AFTER_BIDDING,
                    )
                )
                bidRepository.save(
                    Bidding(
                        price = item.endPrice,
                        item = item,
                        user = user,
                    )
                )
                NO_CONTENT
            }

            else -> {
                itemsRepository.save(
                    item.copy(
                        currentPrice = request.price,
                        biddingStatus = BiddingStatus.IN_BIDDING,
                    )
                )
                SUCCESS
            }
        }
    }
}


