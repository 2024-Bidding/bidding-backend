package com.tmdhoon2.bidding.domain.item.service

import com.tmdhoon2.bidding.domain.item.entity.Likes
import com.tmdhoon2.bidding.domain.item.entity.repository.ItemsRepository
import com.tmdhoon2.bidding.domain.item.entity.repository.LikesRepository
import com.tmdhoon2.bidding.domain.user.entity.repository.UserRepository
import com.tmdhoon2.bidding.global.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LikeItemService(
    private val likesRepository: LikesRepository,
    private val itemsRepository: ItemsRepository,
    private val userRepository: UserRepository,
) {
    fun execute(
        userId: Long,
        itemId: Long,
    ) {
        val item = itemsRepository.findByIdOrNull(itemId) ?: throw NotFoundException("Item not found")
        val user = userRepository.findByIdOrNull(userId) ?: throw NotFoundException("User not found")

        val likes = likesRepository.findAllByUserId(userId)
        val entity = likes.find { it.item.id == itemId }
        if (entity == null) {
            likesRepository.save<Likes>(
                Likes(
                    item = item,
                    user = user,
                )
            )
        } else {
            likesRepository.delete(entity)
        }
    }
}