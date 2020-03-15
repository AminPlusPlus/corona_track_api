package com.amin.corona_track_api.service.impl;

import com.amin.corona_track_api.model.LocationData;
import com.amin.corona_track_api.service.MainDataService;
import com.amin.corona_track_api.service.VirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Scope(value = "prototype")
public class VirusDataServiceImpl implements VirusDataService {

    @Autowired
    private MainDataService mainDataService;

    private List<LocationData> allStats = new ArrayList<>();


    @Override
    public void setBaseURl(String url) {
        try {
            this.allStats = mainDataService.getAllDada(url);
        } catch ( IOException ioException) {
            //todo: handle exception
        }

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

        return new HashMap<>() {{
            put("Total ", sum.toString());
        }};
    }
}
