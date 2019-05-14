package com.travel.test;

import com.travel.service.PathFinder;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TravelTest {
    @Test
    public void testForFlightBetweenTwoCities(){
        PathFinder pathFinder = new PathFinder();
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities("Bangalore","Singapore");
        assertEquals("true", isFlightAvailableResponse);
    }

    @Test
    public void testForUnavailableFlight(){
        PathFinder pathFinder = new PathFinder();
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities("Bangalore","Tokyo");
        assertEquals("false", isFlightAvailableResponse);
    }

    @Test
    public void testForFlightFirstCityNotPresentInDatabase(){
        PathFinder pathFinder = new PathFinder();
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities("Chennai","Tokyo");
        assertEquals("No city named \"Chennai\" in database",isFlightAvailableResponse);
    }

    @Test
    public void testForFlightSecondCityNotPresentInDatabase(){
        PathFinder pathFinder = new PathFinder();
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities("Bangalore","Stockholm");
        assertEquals("No city named \"Stockholm\" in database",isFlightAvailableResponse);
    }
}
