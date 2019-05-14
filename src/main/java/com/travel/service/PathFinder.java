package com.travel.service;

import com.travel.constants.Constants;
import com.travel.entity.Flight;
import com.travel.entity.Tour;

import java.util.StringTokenizer;

public class PathFinder {

    public boolean isAvailableInPathDatabase(String destination){
        StringTokenizer stz;
        for (String path: Constants.Paths.PATHS_ARRAY) {
            stz = new StringTokenizer(path,",");
            String fromDestination = stz.nextToken();
            String toDestination = stz.nextToken();
            if(destination.equals(fromDestination)||destination.equals(toDestination)){
                return true;
            }
        }
        return false;
    }

    public String checkForFlightsBetweenCities(String fromDestination, String toDestination, Tour tour){
        Boolean isFromDestinationPresent = isAvailableInPathDatabase(fromDestination);
        Boolean isToDestinationPresent = isAvailableInPathDatabase(toDestination);
        if(isFromDestinationPresent && isToDestinationPresent){
            return String.valueOf(isDirectFlight(fromDestination,toDestination, tour));
        }
        else {
            return AppResponseService.getResponseIfDestinationNotPresent(fromDestination, toDestination, isFromDestinationPresent);
        }
    }

    public boolean isDirectFlight(String fromDestination, String toDestination, Tour tour){
        return tour.getAvailableFlights().stream().anyMatch((flight) -> isFlightAvailable(fromDestination, toDestination, flight));
    }

    private boolean isFlightAvailable(String fromDestination, String toDestination, Flight flight) {
        return flight.getFromDestination().equals(fromDestination) && flight.getToDestination().equals(toDestination);
    }
}
