package com.tmdhoon2.bidding.domain.item.controller

import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.item.service.FetchItemsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/items")
@RestController
class ItemsController(
    private val fetchItemsService: FetchItemsService,
) {
    @GetMapping
    fun fetchAllItems(): ItemsResponse {
        return ItemsResponse(items = fetchItemsService.execute())
    }
}