package com.tmdhoon2.bidding

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller

@Controller
@SpringBootApplication
class BiddingApplication

fun main(args: Array<String>) {
    runApplication<BiddingApplication>(*args)
}
