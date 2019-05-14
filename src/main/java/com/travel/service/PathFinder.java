package com.travel.service;

import com.travel.entity.Flight;
import com.travel.entity.Tour;
import com.travel.util.DataInitializer;

public class PathFinder {
    public boolean isDirectFlight(String fromDestination, String toDestination){
        Tour tour = DataInitializer.intializeTours();
        for (Flight flight: tour.getAvailableFlights()) {
            if(isFlightAvailable(fromDestination, toDestination, flight)){
                return true;
            }
        }
        return false;
    }

    private boolean isFlightAvailable(String fromDestination, String toDestination, Flight flight) {
        return flight.getFromDestination().equals(fromDestination) && flight.getToDestination().equals(toDestination);
    }
}
