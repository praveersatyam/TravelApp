package com.travel.test;

import com.travel.entity.Tour;
import com.travel.service.PathFinder;
import com.travel.util.DataInitializer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TravelTest {
    @Test
    public void testForFlightBetweenTwoCities(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = pathFinder.findFlightPathsBetweenCities("Bangalore","Singapore", tour);
        assertEquals("Bangalore->Singapore", isFlightAvailableResponse);
    }

    @Test
    public void testForUnavailableDirectFlight(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = pathFinder.findFlightPathsBetweenCities("Bangalore","Tokyo", tour);
        assertEquals("Bangalore->Singapore->Seoul->Beijing->Tokyo", isFlightAvailableResponse);
    }

    @Test
    public void testForAnotherUnavailableDirectFlight(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = pathFinder.findFlightPathsBetweenCities("Tokyo","Bangalore", tour);
        assertEquals("Tokyo->Beijing->Seoul->Singapore->Bangalore", isFlightAvailableResponse);
    }

    @Test
    public void testForFlightFirstCityNotPresentInDatabase(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = pathFinder.findFlightPathsBetweenCities("Chennai","Tokyo", tour);
        assertEquals("No city named \"Chennai\" in database",isFlightAvailableResponse);
    }

    @Test
    public void testForFlightSecondCityNotPresentInDatabase(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = pathFinder.findFlightPathsBetweenCities("Bangalore","Stockholm", tour);
        assertEquals("No city named \"Stockholm\" in database",isFlightAvailableResponse);
    }

    @Test
    public void testForIndirectFlightsCrossingTwoCities(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = String.valueOf(pathFinder.findFlightPathsBetweenCities("Bangalore","Seoul",tour));
        assertEquals("Bangalore->Singapore->Seoul",isFlightAvailableResponse);
    }

    @Test
    public void testForIndirectFlightsCrossingThreeCities(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = String.valueOf(pathFinder.findFlightPathsBetweenCities("Bangalore","Beijing",tour));
        assertEquals("Bangalore->Singapore->Seoul->Beijing",isFlightAvailableResponse);
    }

    @Test
    public void testForBiDirectionalIndirectFlights(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = String.valueOf(pathFinder.findFlightPathsBetweenCities("Seoul","Singapore",tour));
        assertEquals("Seoul->Singapore",isFlightAvailableResponse);
    }

}
