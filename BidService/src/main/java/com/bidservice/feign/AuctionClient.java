package com.bidservice.feign;

import com.bidservice.dto.AuctionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
@FeignClient("AUCTIONSERVICE")
public interface AuctionClient {
    @GetMapping("/api/auction/{auctionId}")
    AuctionDto getAuctionById(@PathVariable Integer auctionId);
}