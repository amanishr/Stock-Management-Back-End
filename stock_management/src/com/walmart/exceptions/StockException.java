package com.walmart.exceptions;

public class StockException extends Exception{

	private static final long serialVersionUID = -3874313643617441447L;
	
	public StockException(String message) {
		super(message);
	}
}
