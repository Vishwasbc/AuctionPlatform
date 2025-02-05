package com.bidservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class AuctionNotFoundException extends RuntimeException {
	public AuctionNotFoundException(String msg) {
		super(msg);
	}
}
