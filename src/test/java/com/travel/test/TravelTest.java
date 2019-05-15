package com.travel.test;

import com.travel.entity.Tour;
import com.travel.service.PathFinder;
import com.travel.util.DataInitializer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TravelTest {
    @Test
    public void testForFlightBetweenTwoCities(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.intializeTours();
        List<Integer>[] listImplementedGraph = DataInitializer.initializeGraph(tour);
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities(listImplementedGraph,"Bangalore","Singapore", tour);
        assertEquals("true", isFlightAvailableResponse);
    }

    @Test
    public void testForUnavailableFlight(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.intializeTours();
        List<Integer>[] listImplementedGraph = DataInitializer.initializeGraph(tour);
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities(listImplementedGraph,"Bangalore","Tokyo", tour);
        assertEquals("true", isFlightAvailableResponse);
    }

    @Test
    public void testForFlightFirstCityNotPresentInDatabase(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.intializeTours();
        List<Integer>[] listImplementedGraph = DataInitializer.initializeGraph(tour);
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities(listImplementedGraph,"Chennai","Tokyo", tour);
        assertEquals("No city named \"Chennai\" in database",isFlightAvailableResponse);
    }

    @Test
    public void testForFlightSecondCityNotPresentInDatabase(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.intializeTours();
        List<Integer>[] listImplementedGraph = DataInitializer.initializeGraph(tour);
        String isFlightAvailableResponse = pathFinder.checkForFlightsBetweenCities(listImplementedGraph,"Bangalore","Stockholm", tour);
        assertEquals("No city named \"Stockholm\" in database",isFlightAvailableResponse);
    }

    @Test
    public void testForIndirectFlightsBetweenTwoIndirectCities(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.intializeTours();
        List<Integer>[] listImplementedGraph = DataInitializer.initializeGraph(tour);
        String isFlightAvailableResponse = String.valueOf(pathFinder.hasIndirectFlight(listImplementedGraph,"Bangalore","Seoul",tour));
        assertEquals("true",isFlightAvailableResponse);
    }

    @Test
    public void testForIndirectFlightsBetweenThreeIndirectCities(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.intializeTours();
        List<Integer>[] listImplementedGraph = DataInitializer.initializeGraph(tour);
        String isFlightAvailableResponse = String.valueOf(pathFinder.hasIndirectFlight(listImplementedGraph,"Bangalore","Beijing",tour));
        assertEquals("true",isFlightAvailableResponse);
    }

    @Test
    public void testForUnavailableIndirectFlights(){
        PathFinder pathFinder = new PathFinder();
        Tour tour = DataInitializer.intializeTours();
        List<Integer>[] listImplementedGraph = DataInitializer.initializeGraph(tour);
        String isFlightAvailableResponse = String.valueOf(pathFinder.hasIndirectFlight(listImplementedGraph,"Seoul","Singapore",tour));
        assertEquals("false",isFlightAvailableResponse);
    }

}
