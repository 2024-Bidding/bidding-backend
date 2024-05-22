package com.tmdhoon2.bidding.domain.item.service

import com.gil.easyjwt.user.CurrentUserService
import com.tmdhoon2.bidding.domain.item.controller.dto.request.CreateItemRequest
import com.tmdhoon2.bidding.domain.item.entity.BiddingStatus
import com.tmdhoon2.bidding.domain.item.entity.Item
import com.tmdhoon2.bidding.domain.item.entity.ItemImage
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemImageRepository
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import com.tmdhoon2.bidding.domain.user.entity.User
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CreateItemService(
    private val itemsRepository: ItemsRepository,
    private val itemImageRepository: ItemImageRepository,
    private val currentUserService: CurrentUserService<User>,
) {
    @Transactional
    operator fun invoke(request: CreateItemRequest) {
        val user = currentUserService.currentUser
        val item = itemsRepository.save(
            request.run {
                Item(
                    name = name,
                    startPrice = startPrice,
                    endPrice = endPrice,
                    startTime = startTime,
                    endTime = endTime,
                    currentPrice = startPrice,
                    biddingStatus = calculateBiddingStatus(
                        startDate = startTime,
                        endDate = endTime,
                    ),
                    content = content,
                    user = user,
                    imageUrl = request.imageUrls.first(),
                )
            },
        )
        itemImageRepository.saveAll(
            request.imageUrls.map {
                ItemImage(
                    imageUrl = it,
                    item = item,
                )
            }
        )
    }

    private fun calculateBiddingStatus(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
    ): BiddingStatus {
        val currentDate = LocalDateTime.now()

        return if (currentDate.isBefore(startDate)) BiddingStatus.BEFORE_BIDDING
        else if (currentDate.isAfter(endDate)) BiddingStatus.AFTER_BIDDING
        else BiddingStatus.IN_BIDDING
    }
}
