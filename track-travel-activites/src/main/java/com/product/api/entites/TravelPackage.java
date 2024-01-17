package com.product.api.entites;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int passengerCapacity;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL)
    private List<Destinations> itinerary;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    // Constructors, getters, setters, and other methods

    public void printItinerary() {
        // Print itinerary of the travel package
    }

    public void printPassengerList() {
        // Print passenger list of the travel package
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	public List<Destinations> getItinerary() {
		return itinerary;
	}

	public void setItinerary(List<Destinations> itinerary) {
		this.itinerary = itinerary;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
    
}

