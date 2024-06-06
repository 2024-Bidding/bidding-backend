package com.tmdhoon2.bidding.domain.bidding.controller

import com.gil.easyjwt.user.CurrentUserService
import com.tmdhoon2.bidding.domain.bidding.controller.dto.request.BidItemRequest
import com.tmdhoon2.bidding.domain.bidding.service.BidItemService
import com.tmdhoon2.bidding.domain.bidding.service.QueryBiddingHistoryService
import com.tmdhoon2.bidding.domain.item.controller.dto.response.ItemsResponse
import com.tmdhoon2.bidding.domain.user.entity.User
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/bid")
@Controller
class BidController(
    private val bidItemService: BidItemService,
    private val queryBiddingHistoryService: QueryBiddingHistoryService,
    private val currentUserService: CurrentUserService<User>,
) {
    @PatchMapping("/{item-id}")
    fun bidItem(
        @PathVariable("item-id") itemId: Long,
        @RequestBody @Valid request: BidItemRequest,
    ): ResponseEntity<Unit> {
        val user = currentUserService.currentUser
        val responseCode = bidItemService(
            itemId = itemId,
            user = user,
            request = request,
        )

        return when (responseCode) {
            BidItemService.SUCCESS -> ResponseEntity(HttpStatus.OK)
            BidItemService.NO_CONTENT -> ResponseEntity(HttpStatus.NO_CONTENT)
            BidItemService.NOT_FOUND -> ResponseEntity(HttpStatus.NOT_FOUND)
            else -> ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/my")
    fun queryBiddingHistory(): ItemsResponse {
        val userId = currentUserService.currentUser.id
        return queryBiddingHistoryService.execute(userId)
    }
}