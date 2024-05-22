package com.tmdhoon2.bidding.domain.item.entity.repository

import com.tmdhoon2.bidding.domain.item.entity.ItemImage
import org.springframework.data.jpa.repository.JpaRepository

interface ItemImageRepository: JpaRepository<ItemImage, Long> {

    fun findAllByItemId(itemId: Long): List<ItemImage>
}
