package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.entity.Item
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class FetchItemsService(
    private val itemsRepository: ItemsRepository,
) {
    @Transactional
    fun execute(): List<Item> {
        return itemsRepository.findAllBy()
    }
}