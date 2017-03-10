package com.cooksys.airline;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by justin on 3/8/17.
 */
public class AirportService {

    private static Map<Long, AirportInfo> airportInfoMapById;
    private static Map<String, AirportInfo> airportInfoMapByIata;
    private static Map<String, AirportInfo> airportInfoMapByIcao;

    public void init() {
        airportInfoMapById = new HashMap<>();
        airportInfoMapByIata = new HashMap<>();
        airportInfoMapByIcao = new HashMap<>();
        Set<String> set = new HashSet<>();
        try {
            String[] lines = Files.readAllLines(new File("src/main/resources/airports.dat").toPath()).toArray(new String[0]);
            for(String line: lines) {
                line = line.replaceAll("[\"]", "");
                AirportInfo airportInfo = populateAirportInfo(line);
                airportInfoMapById.put(airportInfo.getAirportId(), airportInfo);
                airportInfoMapByIata.put(airportInfo.getIata(), airportInfo);
                airportInfoMapByIcao.put(airportInfo.getIcao(), airportInfo);
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
        return airportInfoMapById.get(id);
    }

    public AirportInfo getAirportByIata(String iata) {
        iata = iata.toUpperCase();
        return airportInfoMapByIata.get(iata);
    }

    public AirportInfo getAirportByIcao(String icao) {
        icao = icao.toUpperCase();
        return airportInfoMapByIcao.get(icao);
    }

    public List<AirportInfo> getAirports() {
        return new ArrayList(airportInfoMapById.values());
    }

    public List<AirportInfo> getAirportsByCountry(String country) {
        List<AirportInfo> airportInfoList = new ArrayList<>();
        if(country == null) {
            return airportInfoList;
        }
        country = country.replaceAll("[_-]", " ");
        for(AirportInfo airportInfo : airportInfoMapById.values()) {
            if(country.equalsIgnoreCase(airportInfo.getCountry())) {
                airportInfoList.add(airportInfo);
            }
        }
        return airportInfoList;
    }

    public List<AirportInfo> getAirportsByCity(String city) {
        List<AirportInfo> airportInfoList = new ArrayList<>();
        if(city == null) {
            return airportInfoList;
        }
        city = city.replaceAll("[_-]", " ");
        for(AirportInfo airportInfo : airportInfoMapById.values()) {
            if(city.equalsIgnoreCase(airportInfo.getCity())) {
                airportInfoList.add(airportInfo);
            }
        }
        return airportInfoList;
    }
}
