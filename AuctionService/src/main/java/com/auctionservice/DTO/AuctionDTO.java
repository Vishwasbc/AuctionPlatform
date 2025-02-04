package com.auctionservice.DTO;

import java.time.LocalDateTime;

import com.auctionservice.entity.AuctionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDTO {
	private Integer auctionId;
	private int productId;
	private String description;
	private String sellerName;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private double startPrice;
	private double currentHighestBid;
	private double minBidAmount;
	private AuctionStatus status;
}
