package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.entity.ItemImage
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemImageRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class QueryItemImageService(
    private val itemImageRepository: ItemImageRepository,
) {
    @Transactional
    fun execute(itemId: Long): List<ItemImage> {
        val itemImage = itemImageRepository.findAllByItemId(itemId = itemId)
        return itemImage
    }
}