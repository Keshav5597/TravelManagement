package com.product.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.api.entites.Destinations;

public interface DestinationRepository extends JpaRepository<Destinations, Long>{

	Optional<Destinations> findById(Long destinationId);

	//Object findById(Long destinationId);


}
