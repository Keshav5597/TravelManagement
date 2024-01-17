package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entites.Activity;
import com.product.api.entites.Passenger;
import com.product.api.service.PassengerService;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/create")
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger) {
        try {
            Passenger createdPassenger = passengerService.createPassenger(passenger);
            return new ResponseEntity<>(createdPassenger, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{passengerId}/activities")
    public ResponseEntity<List<Activity>> getPassengerActivities(@PathVariable Long passengerId) {
        List<Activity> activities = passengerService.getPassengerActivities(passengerId);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PostMapping("/{passengerId}/signUpForActivity/{activityId}")
    public ResponseEntity<String> signUpForActivity(
            @PathVariable Long passengerId,
            @PathVariable Long activityId
    ) {
        try {
            passengerService.signUpForActivity(passengerId, activityId);
            return new ResponseEntity<>("Successfully signed up for the activity", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{passengerId}/details")
    public ResponseEntity<String> printPassengerDetails(@PathVariable Long passengerId) {
        passengerService.printPassengerDetails(passengerId);
        return new ResponseEntity<>("Printing passenger details", HttpStatus.OK);
    }
}