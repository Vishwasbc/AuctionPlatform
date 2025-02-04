package com.auctionservice.service;

import java.util.List;

import com.auctionservice.entity.Auction;

public interface AuctionService {
	String createAuction(Auction auction);

	Auction updateAuction(int id, Auction auction);

	List<Auction> getAllAuction();

	Auction getAuctionById(int id);

	String deleteAuction(int id);

	List<Auction> getAuctionBySeller(String name);

	String endAuction(int id);

	String startAuction(int id);
}
