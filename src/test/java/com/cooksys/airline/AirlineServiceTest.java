package com.cooksys.airline;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by justin on 3/9/17.
 */
public class AirlineServiceTest {

    @Test
    public void getAirlineById() throws Exception {
        AirlineService airlineService = new AirlineService();
        airlineService.init();
        assertNotNull("getAirlineById(1) should not return null", airlineService.getAirlineById(1));
    }

    @Test
    public void getAirlines() throws Exception {
        AirlineService airlineService = new AirlineService();
        airlineService.init();
        assertNotNull("getAirlines() should not return null", airlineService.getAirlines());
        assertTrue("getAirlines().size() should be greater than 0", airlineService.getAirlines().size() > 0);
    }

}