package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.api.entites.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
