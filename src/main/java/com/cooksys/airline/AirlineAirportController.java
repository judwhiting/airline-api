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

    @RequestMapping("/airports/countries/{airportCountry}")
    public Map<String, Object> airportInfoByCountry(@PathVariable(value = "airportCountry") String country) {
        final Map<String, Object> responseBody = new HashMap<>();
        List<AirportInfo> airportInfoList = airportService.getAirportsByCountry(country);

        responseBody.put("version", versionMetadata);
        responseBody.put("airports", airportInfoList);
        return responseBody;
    }

    @RequestMapping("/airports/cities/{airportCity}")
    public Map<String, Object> airportInfoByCity(@PathVariable(value = "airportCity") String city) {
        final Map<String, Object> responseBody = new HashMap<>();
        List<AirportInfo> airportInfoList = airportService.getAirportsByCity(city);

        responseBody.put("version", versionMetadata);
        responseBody.put("airports", airportInfoList);
        return responseBody;
    }

    @RequestMapping("/airports/iata/{airportIata}")
    public Map<String, Object> airportInfoByIata(@PathVariable(value = "airportIata") String iata) {
        final Map<String, Object> responseBody = new HashMap<>();
        AirportInfo airportInfo = airportService.getAirportByIata(iata);

        responseBody.put("version", versionMetadata);
        responseBody.put("airports", airportInfo);
        return responseBody;
    }

    @RequestMapping("/airports/icao/{airportIcao}")
    public Map<String, Object> airportInfoByIcao(@PathVariable(value = "airportIcao") String icao) {
        final Map<String, Object> responseBody = new HashMap<>();
        AirportInfo airportInfo = airportService.getAirportByIcao(icao);

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

    @RequestMapping("/airlines/countries/{airlineCountry}")
    public Map<String, Object> airlineInfoByCountry(@PathVariable(value = "airlineCountry") String country) {
        final Map<String, Object> responseBody = new HashMap<>();
        List<AirlineInfo> airlineInfoList = airlineService.getAirlinesByCountry(country);

        responseBody.put("version", versionMetadata);
        responseBody.put("airports", airlineInfoList);
        return responseBody;
    }

    @RequestMapping("/airlines/iata/{airlineIata}")
    public Map<String, Object> airlineInfoByIata(@PathVariable(value = "airlineIata") String iata) {
        final Map<String, Object> responseBody = new HashMap<>();
        AirlineInfo airlineInfo = airlineService.getAirlineByIata(iata);

        responseBody.put("version", versionMetadata);
        responseBody.put("airlines", airlineInfo);
        return responseBody;
    }

    @RequestMapping("/airlines/icao/{airlineIcao}")
    public Map<String, Object> airlineInfoByIcao(@PathVariable(value = "airlineIcao") String icao) {
        final Map<String, Object> responseBody = new HashMap<>();
        AirlineInfo airlineInfo = airlineService.getAirlineByIcao(icao);

        responseBody.put("version", versionMetadata);
        responseBody.put("airlines", airlineInfo);
        return responseBody;
    }
}
