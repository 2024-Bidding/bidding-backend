package com.tmdhoon2.bidding.domain.bidding.entity.repository

import com.tmdhoon2.bidding.domain.bidding.entity.Bidding
import org.springframework.data.jpa.repository.JpaRepository

interface BidRepository : JpaRepository<Bidding, Long>