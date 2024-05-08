package com.tmdhoon2.bidding.domain.item.controller

import com.tmdhoon2.bidding.domain.item.controller.dto.request.BidItemRequest
import com.tmdhoon2.bidding.domain.item.controller.dto.request.CreateItemRequest
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemDetailsResponse
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.service.BidItemService
import com.tmdhoon2.bidding.domain.item.service.CreateItemService
import com.tmdhoon2.bidding.domain.item.service.FetchItemsService
import com.tmdhoon2.bidding.domain.item.service.QueryItemService
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
) {
    @GetMapping
    fun queryItems(): ItemsResponse {
        return ItemsResponse(items = queryItemsService.execute())
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
            )
        }
    }

    @PatchMapping("/bid/{item-id}")
    fun bidItem(
        @PathVariable("item-id") itemId: Long,
        @RequestBody @Valid request: BidItemRequest,
    ): ResponseEntity<Unit> {
        val responseCode = bidItemService(
            itemId = itemId,
            request = request,
        )

        return when (responseCode) {
            BidItemService.SUCCESS -> ResponseEntity(HttpStatus.OK)
            BidItemService.NO_CONTENT -> ResponseEntity(HttpStatus.NO_CONTENT)
            else -> ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}