package com.tmdhoon2.bidding.domain.item.controller

import com.gil.easyjwt.user.CurrentUserService
import com.tmdhoon2.bidding.domain.item.controller.dto.request.BidItemRequest
import com.tmdhoon2.bidding.domain.item.controller.dto.request.CreateItemRequest
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemDetailsResponse
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.service.BidItemService
import com.tmdhoon2.bidding.domain.item.service.CreateItemService
import com.tmdhoon2.bidding.domain.item.service.QueryBiddingHistoryService
import com.tmdhoon2.bidding.domain.item.service.QueryItemDetailsService
import com.tmdhoon2.bidding.domain.item.service.QueryItemsService
import com.tmdhoon2.bidding.domain.user.entity.User
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    private val bidItemService: BidItemService,
    private val queryBiddingHistoryService: QueryBiddingHistoryService,
    private val currentUserService: CurrentUserService<User>,
) {
    @GetMapping
    fun queryItems(): ItemsResponse {
        return queryItemsService.execute()
    }

    @PostMapping
    fun createItem(@RequestBody @Valid request: CreateItemRequest) {
        return createItemService(request = request)
    }

    @GetMapping("/{item-id}")
    fun queryItemDetails(@PathVariable("item-id") itemId: Long): ItemDetailsResponse {
        return queryItemDetailsService.execute(itemId = itemId)
    }

    @PatchMapping("/bid/{item-id}")
    fun bidItem(
        @PathVariable("item-id") itemId: Long,
        @RequestBody @Valid request: BidItemRequest,
    ): ResponseEntity<Unit> {
        val userId = currentUserService.currentUser.id
        val responseCode = bidItemService(
            itemId = itemId,
            userId = userId,
            request = request,
        )

        return when (responseCode) {
            BidItemService.SUCCESS -> ResponseEntity(HttpStatus.OK)
            BidItemService.NO_CONTENT -> ResponseEntity(HttpStatus.NO_CONTENT)
            BidItemService.NOT_FOUND -> ResponseEntity(HttpStatus.NOT_FOUND)
            else -> ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/bid/my")
    fun queryBiddingHistory(): ItemsResponse {
        val userId = currentUserService.currentUser.id
        return queryBiddingHistoryService.execute(userId)
    }
}