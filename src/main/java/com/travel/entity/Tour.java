package com.travel.entity;

import java.util.List;
import java.util.Map;

public class Tour {
    private String tourId;
    private List<Flight> availableFlights;
    private List<Integer>[] graphOfFlights;
    private Map<String, Integer> destinationToIdMap;


    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public List<Flight> getAvailableFlights() {
        return availableFlights;
    }

    public void setAvailableFlights(List<Flight> availableFlights) {
        this.availableFlights = availableFlights;
    }

    public List<Integer>[] getGraphOfFlights() {
        return graphOfFlights;
    }

    public void setGraphOfFlights(List<Integer>[] graphOfFlights) {
        this.graphOfFlights = graphOfFlights;
    }

    public Map<String, Integer> getDestinationToIdMap() {
        return destinationToIdMap;
    }

    public void setDestinationToIdMap(Map<String, Integer> destinationToIdMap) {
        this.destinationToIdMap = destinationToIdMap;
    }
}
