package com.travel.util;

import com.travel.constants.Constants;
import com.travel.entity.Flight;
import com.travel.entity.Tour;

import java.util.*;

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
        tour.setDestinationToIdMap(initializeCityNameToCityIdMap(tour));
        tour.setGraphOfFlights(DataInitializer.initializeGraph(tour));
        return tour;
    }

    public static List<Integer>[] initializeGraph(Tour tour){
        initializeCityNameToCityIdMap(tour);
        List<Integer>[] listImplementedGraph = new List[tour.getDestinationToIdMap().size()+1];
        intializeListArray(listImplementedGraph);
        for (Flight flight: tour.getAvailableFlights()) {
            Integer fromCityId = tour.getDestinationToIdMap().get(flight.getFromDestination());
            Integer toCityId = tour.getDestinationToIdMap().get(flight.getToDestination());
            listImplementedGraph[fromCityId].add(toCityId);
        }
        return listImplementedGraph;
    }

    private static Map<String, Integer> initializeCityNameToCityIdMap(Tour tour){
        Map<String, Integer> cityNameToCityIdMap = new TreeMap<>();        int citySequence=0;
        for (Flight flight: tour.getAvailableFlights()) {
            if(!cityNameToCityIdMap.containsKey(flight.getFromDestination())){
                cityNameToCityIdMap.put(flight.getFromDestination(),++citySequence);
            }
            if(!cityNameToCityIdMap.containsKey(flight.getToDestination())){
                cityNameToCityIdMap.put(flight.getToDestination(),++citySequence);
            }
        }
        return cityNameToCityIdMap;
    }

    private static void intializeListArray(List<Integer>[] listImplementedGraph) {
        for (int i = 0; i < listImplementedGraph.length; i++) {
            listImplementedGraph[i] = new ArrayList<>();
        }
    }
}
