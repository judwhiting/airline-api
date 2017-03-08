package com.cooksys.airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by justin on 3/7/17.
 */
@RestController
public class AirlineAirportController {

    public final AirlineService airlineService;
    public final AirportService airportService;

    @Autowired
    public AirlineAirportController(AirlineService airlineService, AirportService airportService) {
        this.airlineService = airlineService;
        this.airportService = airportService;
    }

    @Value("${eureka.instance.metadataMap.version:default}")
    private String versionMetadata;

    @RequestMapping("/airports")
    public Map<String, Object> airportInfo() {
        final Map<String, Object> responseBody = new HashMap<>();
        List<AirportInfo> airportInfoList = airportService.getAirports();

        responseBody.put("version", versionMetadata);
        responseBody.put("airports", airportInfoList);
        return responseBody;
    }

    @RequestMapping("/airports/{airportId}")
    public Map<String, Object> airportInfo(@PathVariable(value = "airportId") long id) {
        final Map<String, Object> responseBody = new HashMap<>();
        AirportInfo airportInfo = airportService.getAirportById(id);

        responseBody.put("version", versionMetadata);
        responseBody.put("airports", airportInfo);
        return responseBody;
    }

    @RequestMapping("/airlines")
    public Map<String, Object> airlineInfo() {
        final Map<String, Object> responseBody = new HashMap<>();
        List<AirlineInfo> airlineInfoList = airlineService.getAirlines();

        responseBody.put("version", versionMetadata);
        responseBody.put("airlines", airlineInfoList);
        return responseBody;
    }

    @RequestMapping("/airlines/{airlineId}")
    public Map<String, Object> airlineInfo(@PathVariable(value = "airlineId") long id) {
        final Map<String, Object> responseBody = new HashMap<>();
        AirlineInfo airlineInfo = airlineService.getAirlineById(id);

        responseBody.put("version", versionMetadata);
        responseBody.put("airlines", airlineInfo);
        return responseBody;
    }
}
