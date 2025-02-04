package com.bidservice.feign;

import com.bidservice.dto.AuctionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
@FeignClient(name = "auction-service", url = "http://localhost:8082/api/auctions")
public interface AuctionClient {
    @GetMapping("/{auctionId}")
    AuctionDto getAuctionById(@PathVariable Integer auctionId);
}