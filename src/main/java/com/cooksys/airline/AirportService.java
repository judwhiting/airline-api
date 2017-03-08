package com.cooksys.airline;

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
public class AirportService {

    private static Map<Long, AirportInfo> airportInfoMap;

    public void init() {
        airportInfoMap = new HashMap<>();
        try {
            String[] lines = Files.readAllLines(new File("src/main/resources/airports.dat").toPath()).toArray(new String[0]);
            for(String line: lines) {
                line = line.replaceAll("[\"]", "");
                AirportInfo airportInfo = populateAirportInfo(line);
                airportInfoMap.put(airportInfo.getAirportId(), airportInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AirportInfo populateAirportInfo(String line) {
        AirportInfo airportInfo = new AirportInfo();
        String[] data = line.split(",");
        airportInfo.setAirportId(Long.parseLong(data[0]));
        airportInfo.setName(data[1]);
        airportInfo.setCity(data[2]);
        airportInfo.setCountry(data[3]);
        airportInfo.setIata(data[4]);
        airportInfo.setIcao(data[5]);
        airportInfo.setLatitude(data[6]);
        airportInfo.setLongitude(data[7]);
        airportInfo.setAltitude(data[8]);
        airportInfo.setTimezone(data[9]);
        airportInfo.setDst(data[10]);
        airportInfo.setDbTimezone(data[11]);
        airportInfo.setType(data[12]);
        airportInfo.setSource(data[13]);
        return airportInfo;
    }

    public AirportInfo getAirportById(long id) {
        return airportInfoMap.get(id);
    }

    public List<AirportInfo> getAirports() {
        return new ArrayList(airportInfoMap.values());
    }
}
