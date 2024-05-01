package com.tmdhoon2.bidding.domain.item.controller

import com.tmdhoon2.bidding.domain.item.controller.dto.request.CreateItemRequest
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.service.CreateItemService
import com.tmdhoon2.bidding.domain.item.service.FetchItemsService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/items")
@RestController
class ItemsController(
    private val queryItemService: FetchItemsService,
    private val createItemService: CreateItemService,
) {
    @GetMapping
    fun queryItems(): ItemsResponse {
        return ItemsResponse(items = queryItemService.execute())
    }

    @PostMapping
    fun createItem(@RequestBody @Valid request: CreateItemRequest) {
        createItemService(request = request)
    }
}