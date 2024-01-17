package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entites.Passenger;
import com.product.api.service.TravelPackageService;

@RestController
@RequestMapping("/travel-packages")
public class TravelPackageController {

    @Autowired
    private TravelPackageService travelPackageService;

    @GetMapping("/{travelPackageId}/passengers")
    public ResponseEntity<List<Passenger>> getTravelPackagePassengers(@PathVariable Long travelPackageId) {
        List<Passenger> passengers = travelPackageService.getTravelPackagePassengers(travelPackageId);
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    @GetMapping("/{travelPackageId}/itinerary")
    public ResponseEntity<String> printItinerary(@PathVariable Long travelPackageId) {
        travelPackageService.printItinerary(travelPackageId);
        return new ResponseEntity<>("Printing travel package itinerary", HttpStatus.OK);
    }

    @GetMapping("/{travelPackageId}/passenger-list")
    public ResponseEntity<String> printPassengerList(@PathVariable Long travelPackageId) {
        travelPackageService.printPassengerList(travelPackageId);
        return new ResponseEntity<>("Printing travel package passenger list", HttpStatus.OK);
    }
}
