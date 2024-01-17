package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.api.entites.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
