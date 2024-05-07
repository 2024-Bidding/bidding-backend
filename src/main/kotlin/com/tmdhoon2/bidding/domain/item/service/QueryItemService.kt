package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.entity.Item
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import com.tmdhoon2.bidding.global.exception.NotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class QueryItemService(
    private val itemsRepository: ItemsRepository,
) {
    @Transactional
    fun execute(itemId: Long): Item {
        val item = itemsRepository.findById(itemId).getOrNull() ?: throw NotFoundException(message = "not found item")
        return item
    }
}
