package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.controller.dto.request.CreateItemRequest
import com.tmdhoon2.bidding.domain.item.entity.BiddingStatus
import com.tmdhoon2.bidding.domain.item.entity.Item
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreateItemService(
    private val itemsRepository: ItemsRepository,
) {
    @Transactional
    operator fun invoke(request: CreateItemRequest) {
        itemsRepository.save(request.run {
            Item(
                name = name,
                startPrice = startPrice,
                endPrice = endPrice,
                imageUrl = imageUrl,
                biddingStatus = BiddingStatus.BEFORE_BIDDING,
                startTime = startTime,
                endTime = endTime,
                currentPrice = startPrice,
            )
        })
    }
}
