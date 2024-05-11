package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.entity.Item
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QueryUserBidItemsService(
    private val itemsRepository: ItemsRepository,
) {
    @Transactional
    operator fun invoke(userId: Long): List<Item> {
        return itemsRepository.findAll().filter { it.userId == userId }
    }
}