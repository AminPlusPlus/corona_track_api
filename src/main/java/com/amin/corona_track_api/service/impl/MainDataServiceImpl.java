package com.amin.corona_track_api.service.impl;

import com.amin.corona_track_api.model.LocationData;
import com.amin.corona_track_api.service.MainDataService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainDataServiceImpl implements MainDataService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<LocationData> getAllDada (String url) throws IOException {
        List<LocationData> newStats = new ArrayList<>();

        ResponseEntity<String> response = restTemplate
                .exchange(url,
                        HttpMethod.GET,
                        null, String.class);
        StringReader csvBodyReader = new StringReader(response.getBody());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationData locationStat = new LocationData();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));

            //get last Date
            String lastDay = record.get(record.size() - 1);
            //parse
            int latestCases = Integer.parseInt(lastDay.isEmpty() ? "0" : lastDay );
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));

            locationStat.setLatestTotalCases(prevDayCases);
            locationStat.setDiffFromPrevDay((latestCases != 0) ? latestCases - prevDayCases : 0);

            newStats.add(locationStat);
        }
        return newStats;
    }

}
