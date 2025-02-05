package com.auctionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class AuctionNotFound extends RuntimeException{
	public AuctionNotFound(String msg) {
		super(msg);
	}
}
