package com.cooksys.airline;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by justin on 3/8/17.
 */
@Component
public class AirlineService {

    private static Map<Long, AirlineInfo> airlineInfoMapById;
    private static Map<String, AirlineInfo> airlineInfoMapByIata;
    private static Map<String, AirlineInfo> airlineInfoMapByIcao;

    @PostConstruct
    public void init() {
        airlineInfoMapById = new HashMap<>();
        airlineInfoMapByIata = new HashMap<>();
        airlineInfoMapByIcao = new HashMap<>();
        try {
            String[] lines = Files.readAllLines(new File("src/main/resources/airlines.dat").toPath()).toArray(new String[0]);
            for(String line : lines) {
                line = line.replaceAll("[\"]", "");
                AirlineInfo airlineInfo = populateAirlineData(line);
                airlineInfoMapById.put(airlineInfo.getAirlineId(), airlineInfo);
                airlineInfoMapByIata.put(airlineInfo.getIata(), airlineInfo);
                airlineInfoMapByIcao.put(airlineInfo.getIcao(), airlineInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AirlineInfo populateAirlineData(String line) {
        AirlineInfo airlineInfo = new AirlineInfo();
        String[] data = line.split(",");
        airlineInfo.setAirlineId(Long.parseLong(data[0]));
        airlineInfo.setName(data[1]);
        airlineInfo.setAlias(data[2]);
        airlineInfo.setIata(data[3]);
        airlineInfo.setIcao(data[4]);
        airlineInfo.setCallsign(data[5]);
        airlineInfo.setCountry(data[6]);
        airlineInfo.setActive(data[7]);
        return airlineInfo;
    }

    public AirlineInfo getAirlineById(long id) {
        return airlineInfoMapById.get(id);
    }

    public AirlineInfo getAirlineByIata(String iata) {
        iata = iata.toUpperCase();
        return airlineInfoMapByIata.get(iata);
    }

    public AirlineInfo getAirlineByIcao(String icao) {
        icao = icao.toUpperCase();
        return airlineInfoMapByIcao.get(icao);
    }

    public List<AirlineInfo> getAirlines() {
        return new ArrayList(airlineInfoMapById.values());
    }

    public List<AirlineInfo> getAirlinesByCountry(String country) {
        List<AirlineInfo> airlineInfoList = new ArrayList<>();
        if(country == null) {
            return airlineInfoList;
        }
        country = country.replaceAll("[_-]", " ");
        for(AirlineInfo airlineInfo : airlineInfoMapById.values()) {
            if(country.equalsIgnoreCase(airlineInfo.getCountry())) {
                airlineInfoList.add(airlineInfo);
            }
        }
        return airlineInfoList;
    }
}
