package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.controller.dto.response.toItemsResponse
import com.tmdhoon2.bidding.domain.item.entity.BiddingStatus
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QueryItemsService(
    private val itemsRepository: ItemsRepository,
) {
    @Transactional
    fun execute(): ItemsResponse {
        val items = itemsRepository.findAll()
        return items.filter { it.biddingStatus == BiddingStatus.IN_BIDDING }.toItemsResponse()
    }
}