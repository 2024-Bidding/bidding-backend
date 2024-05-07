package com.tmdhoon2.bidding.domain.item.controller

import com.tmdhoon2.bidding.domain.item.controller.dto.request.CreateItemRequest
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemDetailsResponse
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.service.CreateItemService
import com.tmdhoon2.bidding.domain.item.service.FetchItemsService
import com.tmdhoon2.bidding.domain.item.service.QueryItemService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/items")
@RestController
class ItemsController(
    private val queryItemsService: FetchItemsService,
    private val createItemService: CreateItemService,
    private val queryItemService: QueryItemService,
) {
    @GetMapping
    fun queryItems(): ItemsResponse {
        return ItemsResponse(items = queryItemsService.execute())
    }

    @PostMapping
    fun createItem(@RequestBody @Valid request: CreateItemRequest) {
        createItemService(request = request)
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
}