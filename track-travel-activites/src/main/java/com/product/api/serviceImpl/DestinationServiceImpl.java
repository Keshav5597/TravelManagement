package com.product.api.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.product.api.entites.Activity;
import com.product.api.entites.Destinations;
import com.product.api.exception.TravelApiException;
import com.product.api.repository.DestinationRepository;
import com.product.api.service.DestinationService;

@Service
public class DestinationServiceImpl implements DestinationService {
	
	@Autowired
	private DestinationRepository destinationRepository;
	
	@Override
	public List<Activity> getDestinationActivities(Long destinationId) {
        try {
            Destinations destination = destinationRepository.findById(destinationId).get();
            return destination.getActivities();
        } catch (Exception e) {
            throw new TravelApiException("Error retrieving destination activities", e);
        }
    }

}
