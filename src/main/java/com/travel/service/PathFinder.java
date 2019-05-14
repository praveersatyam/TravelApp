package com.travel.service;

import com.travel.constants.Constants;
import com.travel.entity.Flight;
import com.travel.entity.Tour;
import com.travel.util.DataInitializer;

import java.util.StringTokenizer;

public class PathFinder {

    public boolean isAvailableInDatabase(String destination){
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

    public String checkForFlightsBetweenCities(String fromDestination, String toDestination){
        Boolean isFromDestinationPresent = isAvailableInDatabase(fromDestination);
        Boolean isToDestinationPresent = isAvailableInDatabase(toDestination);
        if(isFromDestinationPresent && isToDestinationPresent){
            return String.valueOf(isDirectFlight(fromDestination,toDestination));
        }
        else {
            if(!isFromDestinationPresent){
                return "No city named \""+fromDestination+"\" in database";
            }
            else{
                return "No city named \""+toDestination+"\" in database";
            }
        }
    }
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
