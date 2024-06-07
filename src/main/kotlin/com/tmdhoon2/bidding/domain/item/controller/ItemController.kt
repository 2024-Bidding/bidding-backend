package com.tmdhoon2.bidding.domain.item.controller

import com.gil.easyjwt.user.CurrentUserService
import com.tmdhoon2.bidding.domain.item.controller.dto.request.CreateItemRequest
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemDetailsResponse
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.service.CreateItemService
import com.tmdhoon2.bidding.domain.item.service.LikeItemService
import com.tmdhoon2.bidding.domain.item.service.QueryItemDetailsService
import com.tmdhoon2.bidding.domain.item.service.QueryItemsService
import com.tmdhoon2.bidding.domain.item.service.QueryMyItemsService
import com.tmdhoon2.bidding.domain.user.entity.User
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/items")
@RestController
class ItemController(
    private val queryItemsService: QueryItemsService,
    private val createItemService: CreateItemService,
    private val queryItemDetailsService: QueryItemDetailsService,
    private val queryMyItemsService: QueryMyItemsService,
    private val currentUserService: CurrentUserService<User>,
    private val likeItemService: LikeItemService,
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
        return queryItemDetailsService.execute(itemId = itemId)
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
}