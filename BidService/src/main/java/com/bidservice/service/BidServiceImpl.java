package com.bidservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bidservice.dto.AuctionDto;
import com.bidservice.dto.UserDto;
import com.bidservice.entity.Bid;
import com.bidservice.exception.AuctionNotFoundException;
import com.bidservice.exception.InvalidBidAmountException;
import com.bidservice.exception.InvalidBidderException;
import com.bidservice.feign.AuctionClient;
import com.bidservice.feign.UserClient;
import com.bidservice.repository.BidRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class BidServiceImpl implements BidService {
    private BidRepository bidRepository;
    private UserClient userClient;
    private AuctionClient auctionClient;

	@Override
	public Bid placeBid(Bid bid) {
		//validate Bidder
		UserDto bidder = userClient.getUserByUsername(bid.getBidderName());
        if (bidder == null) {
            throw new InvalidBidderException("Invalid bidder: User not found");
        }
 
        // Validate auction
        AuctionDto auction = auctionClient.getAuctionById(bid.getAuctionId());
        if (auction == null) {
            throw new AuctionNotFoundException("Invalid auction: Auction not found");
        }
 
        // Check bid validity
        if (bid.getBidAmount()<auction.getCurrentHighestBid()+auction.getMinBidAmount()) {
            throw new InvalidBidAmountException("Invalid Bid Amount");
        }
 
        return bidRepository.save(bid);
	}

	@Override
	public List<Bid> getBidsByAuction(int auctionId) {
		return bidRepository.findAllByAuctionId(auctionId);
	}

	@Override
	public double getHighestBid(int auctionId) {
		return bidRepository.findTopByAuctionIdOrderByBidAmountDesc(auctionId);
	}

}
