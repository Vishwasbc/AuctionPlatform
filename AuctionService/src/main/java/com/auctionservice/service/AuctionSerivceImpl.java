package com.auctionservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auctionservice.DTO.AuctionDTO;
import com.auctionservice.entity.Auction;
import com.auctionservice.entity.AuctionStatus;
import com.auctionservice.exception.AuctionNotFound;
import com.auctionservice.repository.AuctionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuctionSerivceImpl implements AuctionService {
	private AuctionRepository auctionRepository;

	@Override
	public String createAuction(Auction auction) {
		auctionRepository.save(auction);
		return "Auction Created";
	}

	@Override
	public Auction updateAuction(int id, Auction auction) {
		Auction existingAuction = auctionRepository.findById(id)
				.orElseThrow(() -> new AuctionNotFound("Auction with id:"+id+" does not exist"));

		existingAuction.setProductId(auction.getProductId());
		existingAuction.setDescription(auction.getDescription());
		existingAuction.setSellerName(auction.getSellerName());
		existingAuction.setStartDate(auction.getStartDate());
		existingAuction.setEndDate(auction.getEndDate());
		existingAuction.setStartPrice(auction.getStartPrice());
		existingAuction.setCurrentHighestBid(auction.getCurrentHighestBid());
		existingAuction.setMinBidAmount(auction.getMinBidAmount());
		existingAuction.setStatus(auction.getStatus());

		return auctionRepository.save(existingAuction);
	}

	@Override
	public List<Auction> getAllAuction() {
		return auctionRepository.findAll();
	}

	@Override
	public Auction getAuctionById(int id) {
		return auctionRepository.findById(id).orElseThrow(() -> new AuctionNotFound("Auction with id:"+id+" does not exist"));
	}

	@Override
	public String deleteAuction(int id) {
		auctionRepository.deleteById(id);
		return "Auction Successfully Deleted";
	}

	@Override
	public List<Auction> getAuctionBySeller(String name) {
		return auctionRepository.findBySellerName(name);
	}

	@Override
	public String endAuction(int id) {
		Auction ongoingAuction = auctionRepository.findById(id)
				.orElseThrow(() -> new AuctionNotFound("Auction does not exist"));
		ongoingAuction.setStatus(AuctionStatus.ENDED);
		auctionRepository.save(ongoingAuction);
		return "Auction Ended";
	}

	@Override
	public String startAuction(int id) {
		Auction existingAuction = auctionRepository.findById(id)
				.orElseThrow(() -> new AuctionNotFound("Auction does not exist"));
		existingAuction.setStatus(AuctionStatus.LIVE);
		auctionRepository.save(existingAuction);
		return "Auction Started";
	}

	@Override
	public AuctionDTO getByAuctionId(int id) {
		Auction auction = auctionRepository.findById(id)
				.orElseThrow(() -> new AuctionNotFound("Auction does not exist"));
		AuctionDTO auctionDTO = new AuctionDTO();
		auctionDTO.setAuctionId(auction.getAuctionId());
		auctionDTO.setProductId(auction.getProductId());
		auctionDTO.setDescription(auction.getDescription());
		auctionDTO.setSellerName(auction.getSellerName());
		auctionDTO.setStartDate(auction.getStartDate());
		auctionDTO.setEndDate(auction.getEndDate());
		auctionDTO.setStartPrice(auction.getStartPrice());
		auctionDTO.setCurrentHighestBid(auction.getCurrentHighestBid());
		auctionDTO.setMinBidAmount(auction.getMinBidAmount());
		return auctionDTO;
	}
	

}
