package com.bidservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bidservice.entity.Bid;
import com.bidservice.service.BidService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/bids")
@AllArgsConstructor
public class BidController {
	private BidService bidService;
	
	@PostMapping
	public ResponseEntity<Bid> placeBid(@RequestBody Bid bid){
		return ResponseEntity.ok(bidService.placeBid(bid));
	}
	@GetMapping("/{auctionId}")
	public ResponseEntity<List<Bid>> getBidsByAuction(@PathVariable int auctionId){
		return ResponseEntity.ok(bidService.getBidsByAuction(auctionId));
	}
	@GetMapping("/highest")
	public ResponseEntity<Double> getHighestBid(@RequestParam int auctionId){
		return ResponseEntity.ok(bidService.getHighestBid(auctionId));
	}
}
