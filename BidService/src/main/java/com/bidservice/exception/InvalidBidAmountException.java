package com.bidservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidBidAmountException extends RuntimeException {
	public InvalidBidAmountException(String msg) {
		super(msg);
	}
}
