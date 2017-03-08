package com.cooksys.airline;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by timd on 3/8/17.
 */
@Configuration
public class AirlineConfiguration {
    @Bean(initMethod = "init")
    public AirportService airportService() {
        return new AirportService();
    }
}
