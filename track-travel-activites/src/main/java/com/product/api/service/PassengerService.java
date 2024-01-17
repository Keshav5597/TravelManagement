package com.product.api.service;

import java.util.List;

import com.product.api.entites.Activity;
import com.product.api.entites.Passenger;

public interface PassengerService {
	
	public Passenger createPassenger(Passenger passenger) throws Exception;
	
	public void printPassengerDetails(Long passengerId);
	
	public void signUpForActivity(Long passengerId, Long activityId);
	
	public List<Activity> getPassengerActivities(Long passengerId);
}
