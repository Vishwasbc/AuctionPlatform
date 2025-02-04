package com.bidservice.exception;

public class AuctionNotFoundException extends RuntimeException {
	public AuctionNotFoundException(String msg) {
		super(msg);
	}
}
