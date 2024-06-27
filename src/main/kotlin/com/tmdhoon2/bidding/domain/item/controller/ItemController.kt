package com.tmdhoon2.bidding.domain.item.controller

import com.gil.easyjwt.user.CurrentUserService
import com.tmdhoon2.bidding.domain.item.controller.dto.request.CreateItemRequest
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemDetailsResponse
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.service.*
import com.tmdhoon2.bidding.domain.user.entity.User
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RequestMapping("/items")
@RestController
class ItemController(
    private val queryItemsService: QueryItemsService,
    private val createItemService: CreateItemService,
    private val queryItemDetailsService: QueryItemDetailsService,
    private val queryMyItemsService: QueryMyItemsService,
    private val currentUserService: CurrentUserService<User>,
    private val likeItemService: LikeItemService,
    private val queryMyLikeItemsService: QueryMyLikeItemsService,
) {
    @GetMapping
    fun queryItems(): ItemsResponse {
        val userId = currentUserService.currentUser.id
        return queryItemsService.execute(userId)
    }

    @PostMapping
    fun createItem(@RequestBody @Valid request: CreateItemRequest) {
        return createItemService(request = request)
    }

    @GetMapping("/{item-id}")
    fun queryItemDetails(@PathVariable("item-id") itemId: Long): ItemDetailsResponse {
        val userId = currentUserService.currentUser.id
        return queryItemDetailsService.execute(
            itemId = itemId,
            userId = userId,
        )
    }

    @GetMapping("/my")
    fun queryMyItems(): ItemsResponse {
        val userId = currentUserService.currentUser.id
        return queryMyItemsService.execute(userId)
    }

    @PatchMapping("/like/{item-id}")
    fun likeItem(@PathVariable("item-id") itemId: Long) {
        val userId = currentUserService.currentUser.id
        return likeItemService.execute(
            userId = userId,
            itemId = itemId,
        )
    }

    @GetMapping("/likes")
    fun queryMyLikeItems(): ItemsResponse {
        val userId = currentUserService.currentUser.id
        return queryMyLikeItemsService.execute(userId = userId)
    }
}