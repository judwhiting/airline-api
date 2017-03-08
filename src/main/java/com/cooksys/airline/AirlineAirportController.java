package com.cooksys.airline;

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

    public static final AirlineController airlineController = new AirlineController();
    public static final AirportController airportController = new AirportController();

    @Value("${eureka.instance.metadataMap.version:default}")
    private String versionMetadata;

    @RequestMapping("/airports")
    public Map<String, Object> airportInfo() {
        final Map<String, Object> responseBody = new HashMap<>();
        List<AirportInfo> airportInfoList = airportController.getAirports();

        responseBody.put("version", versionMetadata);
        responseBody.put("airports", airportInfoList);
        return responseBody;
    }

    @RequestMapping("/airports/{airportId}")
    public Map<String, Object> airportInfo(@PathVariable(value = "airportId") long id) {
        final Map<String, Object> responseBody = new HashMap<>();
        AirportInfo airportInfo = airportController.getAirportById(id);

        responseBody.put("version", versionMetadata);
        responseBody.put("airports", airportInfo);
        return responseBody;
    }

    @RequestMapping("/airlines")
    public Map<String, Object> airlineInfo() {
        final Map<String, Object> responseBody = new HashMap<>();
        List<AirlineInfo> airlineInfoList = airlineController.getAirlines();

        responseBody.put("version", versionMetadata);
        responseBody.put("airlines", airlineInfoList);
        return responseBody;
    }

    @RequestMapping("/airlines/{airlineId}")
    public Map<String, Object> airlineInfo(@PathVariable(value = "airlineId") long id) {
        final Map<String, Object> responseBody = new HashMap<>();
        AirlineInfo airlineInfo = airlineController.getAirlineById(id);

        responseBody.put("version", versionMetadata);
        responseBody.put("airlines", airlineInfo);
        return responseBody;
    }
}
