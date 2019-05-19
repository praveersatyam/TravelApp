package com.travel;

import com.travel.entity.Tour;
import com.travel.service.PathFinder;
import com.travel.util.DataInitializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TravelApplication {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int noOfTestCases = Integer.parseInt(br.readLine());
        StringTokenizer stz;
        while (noOfTestCases-->0){
            stz = new StringTokenizer(br.readLine());
            String fromDestination = stz.nextToken();
            String toDestination = stz.nextToken();
            PathFinder pathFinder = new PathFinder();
            Tour tour = DataInitializer.initializeTours("/Users/praveer.satyam/Projects/TravelApp/src/main/resources/paths");
            System.out.println(pathFinder.findFlightPathsBetweenCities(fromDestination, toDestination, tour));
        }

    }
}
