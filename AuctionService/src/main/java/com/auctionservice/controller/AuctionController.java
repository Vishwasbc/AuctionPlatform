package com.auctionservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auctionservice.dto.AuctionDTO;
import com.auctionservice.entity.Auction;
import com.auctionservice.service.AuctionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auction")
@AllArgsConstructor
public class AuctionController {
	private AuctionService auctionService;

	@PostMapping("/create")
	public ResponseEntity<String> createAuction(@RequestBody Auction auction) {
		return new ResponseEntity<>(auctionService.createAuction(auction), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Auction> updateAuction(@RequestParam int id, @RequestBody Auction auction) {
		return ResponseEntity.ok(auctionService.updateAuction(id, auction));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Auction>> getAllAuction() {
		return ResponseEntity.ok(auctionService.getAllAuction());
	}

	@GetMapping
	public ResponseEntity<Auction> getAuctionById(@RequestParam int id) {
		return ResponseEntity.ok(auctionService.getAuctionById(id));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAuction(@RequestParam int id) {
		return ResponseEntity.ok(auctionService.deleteAuction(id));
	}
	
	@PostMapping("/start")
	public ResponseEntity<String> startAuction(@RequestParam int id){
		return ResponseEntity.ok(auctionService.startAuction(id));
	}
	
	@PostMapping("/end")
	public ResponseEntity<String> endAuction(@RequestParam int id) {
		return ResponseEntity.ok(auctionService.endAuction(id));
	}
	@GetMapping("/{id}")
	public AuctionDTO getByAuctionId(@PathVariable int id) {
		return auctionService.getByAuctionId(id);
	}
	@PostMapping("/{id}/{price}")
	public void updateHighestBid(@PathVariable int id,@PathVariable double price) {
		auctionService.updateHighestBid(id,price);
	}
}
