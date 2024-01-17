package com.product.api.exception;

public class ActivityAtCapacityException extends TravelApiException {
	
	public ActivityAtCapacityException(String message) {
        super(message);
    }

    public ActivityAtCapacityException(String message, Throwable cause) {
        super(message, cause);
    }
}
