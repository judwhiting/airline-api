package com.cooksys.airline;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by justin on 3/9/17.
 */
public class AirportServiceTest {
    @Test
    public void getAirportById() throws Exception {
        AirportService airportService = new AirportService();
        airportService.init();
        assertNotNull("getAirportById(1) should not return null", airportService.getAirportById(1));
    }

    @Test
    public void getAirports() throws Exception {
        AirportService airportService = new AirportService();
        airportService.init();
        assertNotNull("getAirports() should not return null", airportService.getAirports());
        assertTrue("getAirports().size() should be greater than 0", airportService.getAirports().size() > 0);
    }

}