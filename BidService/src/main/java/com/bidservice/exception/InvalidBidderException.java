package com.bidservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class InvalidBidderException extends RuntimeException {
	public InvalidBidderException(String msg) {
		super(msg);
	}
}
