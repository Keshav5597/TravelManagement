package com.product.api.service;

import java.util.List;

import com.product.api.entites.Activity;

public interface DestinationService {
	
	public List<Activity> getDestinationActivities(Long destinationId);
}
