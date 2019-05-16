package com.travel.service;

import com.travel.constants.Constants;
import com.travel.entity.City;
import com.travel.entity.Flight;
import com.travel.entity.Tour;
import com.travel.util.ConvertUtil;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {

    public boolean isAvailableInPathDatabase(String destination) {
        for (String path : Constants.Paths.PATHS_ARRAY) {
            if (path.contains(destination)) return true;
        }
        return false;
    }

    public String findIndirectFlightPath(City fromDestination, City toDestination, Tour tour) {
        Integer fromDestinationId = fromDestination.getId();
        Integer toDestinationId = toDestination.getId();
        boolean[] isVisited = new boolean[tour.getCityList().size() + 1];
        List<Integer> cityIdPathList =  findIndirectFlightPath(new ArrayList<>(),tour.getGraphOfFlights(), fromDestinationId, toDestinationId, isVisited);
        if(null != cityIdPathList && cityIdPathList.size()>0){
            return ConvertUtil.getPathFromCityIdList(cityIdPathList,tour);
        }
        return "false";
    }

    public String findFlightPathsBetweenCities(String fromDestination, String toDestination, Tour tour) {
        Boolean isFromDestinationPresent = isAvailableInPathDatabase(fromDestination);
        Boolean isToDestinationPresent = isAvailableInPathDatabase(toDestination);
        if (isFromDestinationPresent && isToDestinationPresent) {
//            TODO: fix me
            City fromCity = ConvertUtil.getCityByCityName(fromDestination, tour);
            City toCity = ConvertUtil.getCityByCityName(toDestination, tour);
            return String.valueOf(findIndirectFlightPath(fromCity, toCity, tour));
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

    //    TODO: change name -- findIndirectFlightPath, graphOfConnectedCities
//    can it be simplified
    private List<Integer> findIndirectFlightPath(List<Integer> cityInThePathList, List<Integer>[] graphOfConnectedCities, Integer rootNode, Integer destinationNode, boolean[] isVisited) {
        if (rootNode == destinationNode) {
            cityInThePathList.add(rootNode);
            return cityInThePathList;
        } else {
            return addPathForNotVisitedConnectedCities(cityInThePathList, graphOfConnectedCities, rootNode, destinationNode, isVisited);
        }
    }

    private List<Integer> addPathForNotVisitedConnectedCities(List<Integer> cityInThePathList, List<Integer>[] graphOfConnectedCities, Integer rootNode, Integer destinationNode, boolean[] isVisited) {
        isVisited[rootNode] = true;
        cityInThePathList.add(rootNode);
        for (Integer cityId : graphOfConnectedCities[rootNode]) {
            if (!isVisited[cityId]) {
                return findIndirectFlightPath(cityInThePathList, graphOfConnectedCities, cityId, destinationNode, isVisited);
            }
        }
        return null;
    }
}
