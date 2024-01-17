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
import com.product.api.service.ActivityService;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/{activityId}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long activityId) {
        Activity activity = activityService.getActivityById(activityId);
        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    @GetMapping("/checkCapacity/{activityId}")
    public ResponseEntity<Boolean> isActivityAtCapacity(@PathVariable Long activityId) {
        boolean atCapacity = activityService.isActivityAtCapacity(activityId);
        return new ResponseEntity<>(atCapacity, HttpStatus.OK);
    }

    @GetMapping("/byDestination/{destinationId}")
    public ResponseEntity<List<Activity>> getActivitiesByDestination(@PathVariable Long destinationId) {
        List<Activity> activities = activityService.getActivitiesByDestination(destinationId);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity createdActivity = activityService.createActivity(activity);
        return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
    }

    @GetMapping("/printAvailableSpaces")
    public ResponseEntity<String> printActivitiesWithAvailableSpaces() {
        activityService.printActivitiesWithAvailableSpaces();
        return new ResponseEntity<>("Printing activities with available spaces", HttpStatus.OK);
    }
}
