package com.amin.corona_track_api.service.impl;

import com.amin.corona_track_api.model.LocationData;
import com.amin.corona_track_api.service.MainDataService;
import com.amin.corona_track_api.service.VirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeathsServiceImpl implements VirusDataService {

    @Autowired
    private MainDataService mainDataService;

    @Value(value = "${covid19.url.deaths}")
    private String baseURL;

    private List<LocationData> allStats = new ArrayList<>();

    @PostConstruct
    private void getVirusData() throws IOException, InterruptedException {
        this.allStats = mainDataService.getAllDada(baseURL);
    }

    @Override
    public List<LocationData> getAll() {
        return this.allStats;
    }

    @Override
    public List<LocationData> getByCountries(String country) {
        return allStats.stream()
                .filter(c->c.getCountry().equals(country))
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationData> getByState(String state) {
        return allStats.stream()
                .filter(c->c.getState().equals(state))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, String> getTotal() {
        Integer sum = allStats.stream()
                .map(LocationData::getLatestTotalCases)
                .mapToInt(Integer::intValue)
                .sum();

        return new HashMap<String, String>() {{
            put("Total Deaths", sum.toString());
        }};
    }
}
