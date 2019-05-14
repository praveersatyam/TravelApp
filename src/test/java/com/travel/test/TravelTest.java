package com.travel.test;

import com.travel.service.PathFinder;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TravelTest {
    @Test
    public void testForTWoCities(){
        PathFinder pathFinder = new PathFinder();
        Boolean isFlight = pathFinder.isDirectFlight("Bangalore","Singapore");
        assertEquals(true, isFlight);
    }
}
