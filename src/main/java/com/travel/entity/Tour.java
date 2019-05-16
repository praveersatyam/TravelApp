package com.travel.entity;

import java.util.List;

public class Tour {
    private String tourId;
    private List<Flight> availableFlights;
    private List<Integer>[] graphOfFlights;
    private List<City> cityList;

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

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
