package com.product.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.api.entites.TravelPackage;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {

	Optional<TravelPackage> findById(Long travelPackageId);

}
