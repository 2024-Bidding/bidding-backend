package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemDetailsResponse
import com.tmdhoon2.bidding.domain.item.controller.dto.response.toItemDetailsResponse
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemImageRepository
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import com.tmdhoon2.bidding.global.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryItemDetailsService(
    private val itemsRepository: ItemsRepository,
    private val itemImageRepository: ItemImageRepository,
) {
    @Transactional(readOnly = true)
    fun execute(itemId: Long): ItemDetailsResponse {
        val item = itemsRepository.findByIdOrNull(itemId) ?: throw NotFoundException(message = "not found item")
        val itemImages = itemImageRepository.findAllByItemId(itemId)
        return item.toItemDetailsResponse(imageUrls = itemImages.map { it.imageUrl })
    }
}
