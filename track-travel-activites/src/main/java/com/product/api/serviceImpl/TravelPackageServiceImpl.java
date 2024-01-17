package com.product.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.api.entites.Activity;
import com.product.api.entites.Destinations;
import com.product.api.entites.Passenger;
import com.product.api.entites.TravelPackage;
import com.product.api.exception.TravelApiException;
import com.product.api.repository.TravelPackageRepository;
import com.product.api.service.TravelPackageService;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {

	@Autowired
	private TravelPackageRepository travelPackageRepository;

	@Override
	public List<Passenger> getTravelPackagePassengers(Long travelPackageId) {
		try {
			TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId).get();

			return travelPackage.getPassengers();
		} catch (Exception e) {
			throw new TravelApiException("Error retrieving travel package passengers", e);
		}
	}
	
	@Override
	public void printItinerary(Long travelPackageId) {
        TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId).get();

        if (travelPackage != null) {
            System.out.println("Travel Package Name: " + travelPackage.getName());

            List<Destinations> itinerary = travelPackage.getItinerary();

            for (Destinations destination : itinerary) {
                System.out.println("Destination: " + destination.getName());

                List<Activity> activities = destination.getActivities();

                for (Activity activity : activities) {
                    System.out.println("Activity Name: " + activity.getName());
                    System.out.println("Cost: " + activity.getCost());
                    System.out.println("Capacity: " + activity.getCapacity());
                    System.out.println("Description: " + activity.getDescription());
                    System.out.println("------------");
                }

                System.out.println("==========================================");
            }
        } else {
            System.out.println("Travel Package not found with ID: " + travelPackageId);
        }
    }

	@Override
	public void printPassengerList(Long travelPackageId) {
        TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId).get();

        if (travelPackage != null) {
            System.out.println("Travel Package Name: " + travelPackage.getName());
            System.out.println("Passenger Capacity: " + travelPackage.getPassengerCapacity());

            List<Passenger> passengers = travelPackage.getPassengers();

            System.out.println("Number of Passengers Enrolled: " + passengers.size());

            for (Passenger passenger : passengers) {
                System.out.println("Passenger Name: " + passenger.getName());
                System.out.println("Passenger Number: " + passenger.getPassengerNumber());
                System.out.println("------------");
            }
        } else {
            System.out.println("Travel Package not found with ID: " + travelPackageId);
        }
    }

}
