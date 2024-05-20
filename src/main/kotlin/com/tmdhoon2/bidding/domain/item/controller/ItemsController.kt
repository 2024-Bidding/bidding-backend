package com.tmdhoon2.bidding.domain.item.controller

import com.gil.easyjwt.user.CurrentUserService
import com.tmdhoon2.bidding.domain.item.controller.dto.request.BidItemRequest
import com.tmdhoon2.bidding.domain.item.controller.dto.request.CreateItemRequest
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemDetailsResponse
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.service.*
import com.tmdhoon2.bidding.domain.user.entity.User
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/items")
@RestController
class ItemsController(
    private val queryItemsService: FetchItemsService,
    private val createItemService: CreateItemService,
    private val queryItemService: QueryItemService,
    private val bidItemService: BidItemService,
    private val queryUserBidItemsService: QueryUserBidItemsService,
    private val currentUserService: CurrentUserService<User>,
) {
    @GetMapping
    fun queryItems(): ItemsResponse {
        return ItemsResponse(
            items = queryItemsService.execute().map {
                ItemsResponse.ItemResponse(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl,
                    endTime = it.endTime,
                    currentPrice = it.currentPrice,
                    userName = it.userName,
                    userProfileUrl = it.userProfileImageUrl,
                )
            },
        )
    }

    @PostMapping
    fun createItem(@RequestBody @Valid request: CreateItemRequest) {
        return createItemService(request = request)
    }

    @GetMapping("/{item-id}")
    fun queryItem(@PathVariable("item-id") itemId: Long): ItemDetailsResponse {
        return queryItemService.execute(itemId = itemId).run {
            ItemDetailsResponse(
                name = this.name,
                currentPrice = this.currentPrice,
                maxPrice = this.endPrice,
                imageUrls = listOf(this.imageUrl),
                startTime = this.startTime.toString(),
                endTime = this.endTime.toString(),
                biddingStatus = this.biddingStatus,
                userName = this.userName,
                content = this.content,
            )
        }
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
        val user = currentUserService.currentUser
        return ItemsResponse(
            items = queryUserBidItemsService(userId = user.id).map {
                ItemsResponse.ItemResponse(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl,
                    endTime = it.endTime,
                    currentPrice = it.currentPrice,
                    userName = it.userName,
                    userProfileUrl = it.userProfileImageUrl,
                )
            },
        )
    }
}