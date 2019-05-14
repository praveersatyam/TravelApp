package com.travel.util;

import com.travel.constants.Constants;
import com.travel.entity.Flight;
import com.travel.entity.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DataInitializer {
    public static List<Flight> intializeFlights(){
        List<Flight> flightList = new ArrayList<>();
        StringTokenizer stz ;
        for (int i = 0; i < Constants.Paths.PATHS_ARRAY.length; i++) {
            stz = new StringTokenizer(Constants.Paths.PATHS_ARRAY[i],",");
            Flight flight = new Flight();
            flight.setFromDestination(stz.nextToken());
            flight.setToDestination(stz.nextToken());
            flightList.add(flight);
        }
        return flightList;
    }

    public static Tour intializeTours(){
        Tour tour = new Tour();
        tour.setAvailableFlights(DataInitializer.intializeFlights());
        return tour;
    }
}
