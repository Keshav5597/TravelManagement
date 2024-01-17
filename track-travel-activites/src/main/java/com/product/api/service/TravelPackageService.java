package com.product.api.service;

import java.util.List;

import com.product.api.entites.Passenger;

public interface TravelPackageService {
	
	public List<Passenger> getTravelPackagePassengers(Long travelPackageId);
	
	public void printItinerary(Long travelPackageId);
	
	public void printPassengerList(Long travelPackageId);
}
