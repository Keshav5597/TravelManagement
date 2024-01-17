package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entites.Activity;
import com.product.api.service.DestinationService;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping("/{destinationId}/activities")
    public ResponseEntity<List<Activity>> getDestinationActivities(@PathVariable Long destinationId) {
        List<Activity> activities = destinationService.getDestinationActivities(destinationId);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

}
