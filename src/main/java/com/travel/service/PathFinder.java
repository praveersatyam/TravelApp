package com.travel.service;

import com.travel.constants.Constants;
import com.travel.entity.Flight;
import com.travel.entity.Tour;
import com.travel.util.DataInitializer;

import java.util.List;
import java.util.StringTokenizer;

public class PathFinder {

    public boolean isAvailableInPathDatabase(String destination) {
        StringTokenizer stz;
        for (String path : Constants.Paths.PATHS_ARRAY) {
            stz = new StringTokenizer(path, ",");
            String fromDestination = stz.nextToken();
            String toDestination = stz.nextToken();
            if (destination.equals(fromDestination) || destination.equals(toDestination)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasIndirectFlight(String fromDestination, String toDestination, Tour tour) {
        List<Integer>[] listImplementedGraph = DataInitializer.initializeGraph(tour);
        Integer fromDestinationId = DataInitializer.cityNameToCityIdMap.get(fromDestination);
        Integer toDestinationId = DataInitializer.cityNameToCityIdMap.get(toDestination);
        boolean[] isVisited = new boolean[DataInitializer.cityNameToCityIdMap.size() + 1];
        return searchForIndirectFlight(listImplementedGraph, fromDestinationId, toDestinationId, isVisited);
    }

    public String checkForFlightsBetweenCities(String fromDestination, String toDestination, Tour tour) {
        Boolean isFromDestinationPresent = isAvailableInPathDatabase(fromDestination);
        Boolean isToDestinationPresent = isAvailableInPathDatabase(toDestination);
        if (isFromDestinationPresent && isToDestinationPresent) {
            return String.valueOf(hasDirectFlight(fromDestination, toDestination, tour)|| hasIndirectFlight(fromDestination, toDestination, tour));
        } else {
            return AppResponseService.getResponseIfDestinationNotPresent(fromDestination, toDestination, isFromDestinationPresent);
        }
    }

    public boolean hasDirectFlight(String fromDestination, String toDestination, Tour tour) {
        return tour.getAvailableFlights().stream().anyMatch((flight) -> isFlightAvailable(fromDestination, toDestination, flight));
    }

    private boolean isFlightAvailable(String fromDestination, String toDestination, Flight flight) {
        return flight.getFromDestination().equals(fromDestination) && flight.getToDestination().equals(toDestination);
    }

    private boolean searchForIndirectFlight(List<Integer>[] listImplementedGraph, Integer rootNode, Integer destinationNode, boolean[] isVisited) {
        if (rootNode == destinationNode) {
            return true;
        } else {
            isVisited[rootNode] = true;
            for (Integer cityId : listImplementedGraph[rootNode]) {
                if (!isVisited[cityId]) {
                    return searchForIndirectFlight(listImplementedGraph, cityId, destinationNode, isVisited);
                }
            }
        }
        return false;
    }
}
