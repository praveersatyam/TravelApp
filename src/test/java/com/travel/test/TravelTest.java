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
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities("Bangalore","Singapore", tour);
        assertEquals("true", isFlightAvailableResponse);
    }

    @Test
    public void testForUnavailableFlight(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities("Bangalore","Tokyo", tour);
        assertEquals("true", isFlightAvailableResponse);
    }

    @Test
    public void testForFlightFirstCityNotPresentInDatabase(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities("Chennai","Tokyo", tour);
        assertEquals("No city named \"Chennai\" in database",isFlightAvailableResponse);
    }

    @Test
    public void testForFlightSecondCityNotPresentInDatabase(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities("Bangalore","Stockholm", tour);
        assertEquals("No city named \"Stockholm\" in database",isFlightAvailableResponse);
    }

    @Test
    public void testForIndirectFlightsCrossingTwoCities(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = String.valueOf(pathFinder.checkForFlightsBetweenCities("Bangalore","Seoul",tour));
        assertEquals("true",isFlightAvailableResponse);
    }

    @Test
    public void testForIndirectFlightsCrossingThreeCities(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = String.valueOf(pathFinder.checkForFlightsBetweenCities("Bangalore","Beijing",tour));
        assertEquals("true",isFlightAvailableResponse);
    }

    @Test
    public void testForUnavailableIndirectFlights(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours();
        String isFlightAvailableResponse = String.valueOf(pathFinder.checkForFlightsBetweenCities("Seoul","Singapore",tour));
        assertEquals("false",isFlightAvailableResponse);
    }

}
