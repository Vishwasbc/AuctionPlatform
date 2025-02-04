package com.auctionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
	private int productId;
	private String productName;
	private String productDescription;
	private double price;
	private String sellerName;
}
