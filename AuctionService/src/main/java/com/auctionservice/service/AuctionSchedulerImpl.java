package com.auctionservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.auctionservice.entity.Auction;
import com.auctionservice.entity.AuctionStatus;
import com.auctionservice.repository.AuctionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuctionSchedulerImpl implements AuctionScheduler {
	private AuctionRepository auctionRepository;
	@Override
	@Scheduled(fixedRate = 60000)//Runs Every Minute
	public void startAuctions() {
		List<Auction> upcomingAuctions=auctionRepository.findByStatus(AuctionStatus.UPCOMING);
		LocalDateTime now=LocalDateTime.now();
		for(Auction auction: upcomingAuctions) {
			if(auction.getStartDate().isBefore(now)) {
				auction.setStatus(AuctionStatus.LIVE);
				auctionRepository.save(auction);
			}
		}
	}
	@Override
	@Scheduled(fixedRate=60000)
	public void endAuctions() {
	    List<Auction> ongoingAuctions = auctionRepository.findByStatus(AuctionStatus.LIVE);
	    LocalDateTime now = LocalDateTime.now();
	    for(Auction auction : ongoingAuctions) {
	        if(auction.getEndDate().isBefore(now)) {
	            auction.setStatus(AuctionStatus.ENDED);
	            auctionRepository.save(auction);
	        }
	    }
	}
}
