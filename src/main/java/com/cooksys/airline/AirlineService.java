package com.cooksys.airline;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by justin on 3/8/17.
 */
@Component
public class AirlineService {

    private static Map<Long, AirlineInfo> airlineInfoMap;

    @PostConstruct
    public void init() {
        airlineInfoMap = new HashMap<>();
        try {
            String[] lines = Files.readAllLines(new File("src/main/resources/airlines.dat").toPath()).toArray(new String[0]);
            for(String line : lines) {
                line = line.replaceAll("[\"]", "");
                AirlineInfo airlineInfo = populateAirlineData(line);
                airlineInfoMap.put(airlineInfo.getAirlineId(), airlineInfo);
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
        return airlineInfoMap.get(id);
    }

    public List<AirlineInfo> getAirlines() {
        return new ArrayList(airlineInfoMap.values());
    }
}
