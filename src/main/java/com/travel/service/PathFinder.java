package com.travel.service;

import com.travel.constants.Constants;
import com.travel.entity.City;
import com.travel.entity.Flight;
import com.travel.entity.Tour;
import com.travel.util.DataInitializer;

import java.util.List;

public class PathFinder {

    public boolean isAvailableInPathDatabase(String destination) {
        for (String path : Constants.Paths.PATHS_ARRAY) {
            if (path.contains(destination)) return true;
        }
        return false;
    }

    public boolean hasIndirectFlight(City fromDestination, City toDestination, Tour tour) {
        Integer fromDestinationId = fromDestination.getId();
        Integer toDestinationId = toDestination.getId();
        boolean[] isVisited = new boolean[tour.getCityList().size() + 1];
        return checkIndirectFlight(tour.getGraphOfFlights(), fromDestinationId, toDestinationId, isVisited);
    }

    public String checkForFlightsBetweenCities(String fromDestination, String toDestination, Tour tour) {
        Boolean isFromDestinationPresent = isAvailableInPathDatabase(fromDestination);
        Boolean isToDestinationPresent = isAvailableInPathDatabase(toDestination);
        if (isFromDestinationPresent && isToDestinationPresent) {
//            TODO: fix me
            City fromCity = DataInitializer.getCityByCityName(fromDestination, tour);
            City toCity = DataInitializer.getCityByCityName(toDestination, tour);
            return String.valueOf(hasIndirectFlight(fromCity, toCity, tour));
        } else {
            return AppResponseService.getResponseIfDestinationNotPresent(fromDestination, toDestination, isFromDestinationPresent);
        }
    }

    private boolean hasDirectFlight(String fromDestination, String toDestination, Tour tour) {
        return tour.getAvailableFlights().stream().anyMatch((flight) -> isFlightAvailable(fromDestination, toDestination, flight));
    }

    private boolean isFlightAvailable(String fromDestination, String toDestination, Flight flight) {
        return flight.getFromDestination().equals(fromDestination) && flight.getToDestination().equals(toDestination);
    }

    //    TODO: change name -- checkIndirectFlight, graphOfConnectedCities
//    can it be simplified
    private boolean checkIndirectFlight(List<Integer>[] graphOfConnectedCities, Integer rootNode, Integer destinationNode, boolean[] isVisited) {
        if (rootNode == destinationNode) {
            return true;
        } else {
            return checkForNotVisitedConnectedCities(graphOfConnectedCities, rootNode, destinationNode, isVisited);
        }
    }

    private Boolean checkForNotVisitedConnectedCities(List<Integer>[] graphOfConnectedCities, Integer rootNode, Integer destinationNode, boolean[] isVisited) {
        isVisited[rootNode] = true;
        for (Integer cityId : graphOfConnectedCities[rootNode]) {
            if (!isVisited[cityId]) {
                return checkIndirectFlight(graphOfConnectedCities, cityId, destinationNode, isVisited);
            }
        }
        return false;
    }
}
