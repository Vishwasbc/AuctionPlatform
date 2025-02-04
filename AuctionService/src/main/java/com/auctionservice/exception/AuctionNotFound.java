package com.auctionservice.exception;

public class AuctionNotFound extends RuntimeException{
	public AuctionNotFound(String msg) {
		super(msg);
	}
}
