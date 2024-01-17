package com.product.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.api.entites.Activity;
import com.product.api.exception.TravelApiException;
import com.product.api.repository.ActivityRepository;
import com.product.api.service.ActivityService;
@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Activity getActivityById(Long activityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isActivityAtCapacity(Long activityId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Activity> getActivitiesByDestination(Long destinationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity createActivity(Activity activity) {
        try {
            return activityRepository.save(activity);
        } catch (Exception e) {
            throw new TravelApiException("Error creating activity", e);
        }
    }

	@Override
	 public void printActivitiesWithAvailableSpaces() {
        Iterable<Activity> activities = activityRepository.findAll();

        System.out.println("Activities with Available Spaces:");

        for (Activity activity : activities) {
            if (!activity.isAtCapacity()) {
                int availableSpaces = activity.getCapacity() - activity.getPassengers().size();

                System.out.println("Activity Name: " + activity.getName());
                System.out.println("Available Spaces: " + availableSpaces);
                System.out.println("Description: " + activity.getDescription());
                System.out.println("------------");
            }
        }
    }

}
