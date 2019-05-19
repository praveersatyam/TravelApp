package com.travel.test;

import com.travel.entity.Tour;
import com.travel.service.PathFinder;
import com.travel.util.DataInitializer;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TravelTest {
    @Test
    public void testForFlightBetweenTwoCities() throws IOException {
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours("/Users/praveer.satyam/Projects/TravelApp/src/main/resources/paths");
        String isFlightAvailableResponse = pathFinder.findFlightPathsBetweenCities("Bangalore","Singapore", tour);
        assertEquals("Bangalore->Singapore", isFlightAvailableResponse);
    }

    @Test
    public void testForUnavailableDirectFlight() throws IOException {
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours("/Users/praveer.satyam/Projects/TravelApp/src/main/resources/paths");
        String isFlightAvailableResponse = pathFinder.findFlightPathsBetweenCities("Bangalore","Tokyo", tour);
        assertEquals("Bangalore->Singapore->Seoul->Beijing->Tokyo", isFlightAvailableResponse);
    }

    @Test
    public void testForAnotherUnavailableDirectFlight() throws IOException {
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours("/Users/praveer.satyam/Projects/TravelApp/src/main/resources/paths");
        String isFlightAvailableResponse = pathFinder.findFlightPathsBetweenCities("Tokyo","Bangalore", tour);
        assertEquals("Tokyo->Beijing->Seoul->Singapore->Bangalore", isFlightAvailableResponse);
    }

    @Test
    public void testForFlightFirstCityNotPresentInDatabase() throws IOException {
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours("/Users/praveer.satyam/Projects/TravelApp/src/main/resources/paths");
        String isFlightAvailableResponse = pathFinder.findFlightPathsBetweenCities("Chennai","Tokyo", tour);
        assertEquals("No city named \"Chennai\" in database",isFlightAvailableResponse);
    }

    @Test
    public void testForFlightSecondCityNotPresentInDatabase() throws IOException {
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours("/Users/praveer.satyam/Projects/TravelApp/src/main/resources/paths");
        String isFlightAvailableResponse = pathFinder.findFlightPathsBetweenCities("Bangalore","Stockholm", tour);
        assertEquals("No city named \"Stockholm\" in database",isFlightAvailableResponse);
    }

    @Test
    public void testForIndirectFlightsCrossingTwoCities() throws IOException {
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours("/Users/praveer.satyam/Projects/TravelApp/src/main/resources/paths");
        String isFlightAvailableResponse = String.valueOf(pathFinder.findFlightPathsBetweenCities("Bangalore","Seoul",tour));
        assertEquals("Bangalore->Singapore->Seoul",isFlightAvailableResponse);
    }

    @Test
    public void testForIndirectFlightsCrossingThreeCities() throws IOException {
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours("/Users/praveer.satyam/Projects/TravelApp/src/main/resources/paths");
        String isFlightAvailableResponse = String.valueOf(pathFinder.findFlightPathsBetweenCities("Bangalore","Beijing",tour));
        assertEquals("Bangalore->Singapore->Seoul->Beijing",isFlightAvailableResponse);
    }

    @Test
    public void testForBiDirectionalIndirectFlights() throws IOException {
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.initializeTours("/Users/praveer.satyam/Projects/TravelApp/src/main/resources/paths");
        String isFlightAvailableResponse = String.valueOf(pathFinder.findFlightPathsBetweenCities("Seoul","Singapore",tour));
        assertEquals("Seoul->Singapore",isFlightAvailableResponse);
    }

}
