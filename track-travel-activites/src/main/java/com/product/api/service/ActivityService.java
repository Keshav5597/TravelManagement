package com.product.api.service;

import java.util.List;

import com.product.api.entites.Activity;

public interface ActivityService {
	
	public Activity getActivityById(Long activityId);
	
	public boolean isActivityAtCapacity(Long activityId);
	
	public List<Activity> getActivitiesByDestination(Long destinationId);
	
	public Activity createActivity(Activity activity);
	
	 public void printActivitiesWithAvailableSpaces();
}


