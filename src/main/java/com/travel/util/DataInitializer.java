package com.travel.util;

import com.travel.constants.Constants;
import com.travel.entity.City;
import com.travel.entity.Flight;
import com.travel.entity.Tour;

import java.util.*;

public class DataInitializer {
    public static List<Flight> intializeFlights(Tour tour) {
        List<Flight> flightList = new ArrayList<>();
        StringTokenizer stz;
        for (int i = 0; i < Constants.Paths.PATHS_ARRAY.length; i++) {
            stz = new StringTokenizer(Constants.Paths.PATHS_ARRAY[i], ",");
            City fromDestination = getCityByCityName(stz.nextToken(), tour);
            City toDestination = getCityByCityName(stz.nextToken(), tour);
            Flight flight = new Flight();
            flight.setFromDestination(fromDestination);
            flight.setToDestination(toDestination);
            flightList.add(flight);
        }
        return flightList;
    }

    public static City getCityByCityName(String destinationName, Tour tour) {
        for (City city : tour.getCityList()) {
            if (city.getCityName().equals(destinationName))
                return city;
        }
        return null;
    }

    public static Tour intializeTours() {
        Tour tour = new Tour();
        tour.setCityList(intializeCityList());
        tour.setAvailableFlights(intializeFlights(tour));
        tour.setGraphOfFlights(initializeGraph(tour));
        return tour;
    }

    public static Map<String, Integer> getCityNameToIdMap() {
        Map<String, Integer> cityNameToIdMap = new TreeMap<>();
        Integer cityIdSequence = 0;
        StringTokenizer stz;
        for (int i = 0; i < Constants.Paths.PATHS_ARRAY.length; i++) {
            stz = new StringTokenizer(Constants.Paths.PATHS_ARRAY[i], ",");
            String fromDestination = stz.nextToken();
            String toDestination = stz.nextToken();
            if (!cityNameToIdMap.containsKey(fromDestination)) {
                cityNameToIdMap.put(fromDestination, ++cityIdSequence);
            }
            if (cityNameToIdMap.containsKey(toDestination)) {
                cityNameToIdMap.put(toDestination, ++cityIdSequence);
            }
        }
        return cityNameToIdMap;
    }

    public static List<Integer>[] initializeGraph(Tour tour) {
        List<Integer>[] listImplementedGraph = new List[tour.getCityList().size() + 1];
        intializeListArray(listImplementedGraph);
        for (Flight flight : tour.getAvailableFlights()) {
            listImplementedGraph[flight.getFromDestination().getId()].add(flight.getToDestination().getId());
        }
        return listImplementedGraph;
    }

    public static List<City> intializeCityList() {
        Set<String> cityNameSet = new HashSet<>();
        Integer cityIdSequence = 0;
        List<City> cityList = new ArrayList<>();
        StringTokenizer stz;
        for (int i = 0; i < Constants.Paths.PATHS_ARRAY.length; i++) {
            stz = new StringTokenizer(Constants.Paths.PATHS_ARRAY[i], ",");
            City fromDestination = new City();
            City toDestination = new City();
            fromDestination.setCityName(stz.nextToken());
            toDestination.setCityName(stz.nextToken());
            if (!cityNameSet.contains(fromDestination.getCityName())) {
                fromDestination.setId(++cityIdSequence);
                cityList.add(fromDestination);
            }
            if (!cityNameSet.contains(toDestination.getCityName())) {
                toDestination.setId(++cityIdSequence);
                cityList.add(toDestination);
            }
        }
        return cityList;
    }


    private static void intializeListArray(List<Integer>[] listImplementedGraph) {
        for (int i = 0; i < listImplementedGraph.length; i++) {
            listImplementedGraph[i] = new ArrayList<>();
        }
    }
}
