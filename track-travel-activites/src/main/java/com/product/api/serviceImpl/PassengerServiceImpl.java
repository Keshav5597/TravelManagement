package com.product.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.product.api.entites.Activity;
import com.product.api.entites.Passenger;
import com.product.api.enums.PassengerType;
import com.product.api.exception.ActivityAtCapacityException;
import com.product.api.exception.TravelApiException;
import com.product.api.repository.ActivityRepository;
import com.product.api.repository.PassengerRepository;
import com.product.api.service.ActivityService;
import com.product.api.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;
	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private ActivityService activityService;

	public Passenger createPassenger(Passenger passenger) throws Exception {
		try {
			return passengerRepository.save(passenger);
		} catch (Exception e) {
			throw new TravelApiException("passeneger is not created", e);
		}
	}

	public List<Activity> getPassengerActivities(Long passengerId) {
		try {
			Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(() -> new NotFoundException());

			return passenger.getActivities();
		} catch (Exception e) {
			throw new TravelApiException("Error retrieving passenger activities", e);
		}
	}
	@Override
	public void signUpForActivity(Long passengerId, Long activityId) {
		try {
			Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(() -> new NotFoundException());

			Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new NotFoundException());

			if (activity.isAtCapacity()) {
				throw new ActivityAtCapacityException("Activity has reached its capacity. Cannot sign up.");
			}

			PassengerType passengerType = passenger.getType();
			double activityCost = activity.getCost();
			double discount = 0.0;

			switch (passengerType) {
			case STANDARD:
				if (passenger.getBalance() < activityCost) {
					throw new TravelApiException("Insufficient balance. Cannot sign up for the activity.");
				}
				passenger.setBalance(passenger.getBalance() - activityCost);
				break;

			case GOLD:
				discount = 0.10; // 10% discount for gold passengers
				double discountedAmount = activityCost * discount;
				if (passenger.getBalance() < (activityCost - discountedAmount)) {
					throw new TravelApiException("Insufficient balance. Cannot sign up for the activity.");
				}
				passenger.setBalance(passenger.getBalance() - (activityCost - discountedAmount));
				break;

			case PREMIUM:
				// Premium passengers can sign up for activities for free
				break;

			default:
				throw new TravelApiException("Invalid passenger type");
			}

			List<Activity> passengerActivities = passenger.getActivities();
			passengerActivities.add(activity);
			passenger.setActivities(passengerActivities);

			passengerRepository.save(passenger);
		} catch (Exception e) {
			throw new TravelApiException("Error signing up for activity", e);
		}
	}

	@Override
	public void printPassengerDetails(Long passengerId) {
		Passenger passenger = passengerRepository.findById(passengerId).orElse(null);

		if (passenger != null) {
			System.out.println("Passenger Name: " + passenger.getName());
			System.out.println("Passenger Number: " + passenger.getPassengerNumber());

			// Check if the passenger has a balance
			Double balance = passenger.getBalance();
			if (balance != null) {
				System.out.println("Balance: " + passenger.getBalance());
			}

			List<Activity> activities = passenger.getActivities();

			if (!activities.isEmpty()) {
				System.out.println("Activities Signed Up:");

				for (Activity activity : activities) {
					System.out.println("Activity Name: " + activity.getName());
					System.out.println("Destination: " + activity.getDestination().getName());
					System.out.println("Price Paid: " + activity.getCost());
					System.out.println("------------");
				}
			} else {
				System.out.println("No activities signed up.");
			}
		} else {
			System.out.println("Passenger not found with ID: " + passengerId);
		}
	}

}
