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

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainDataServiceImpl implements MainDataService {

    @Autowired
    RestTemplate restTemplate;

    String baseURL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";


    private List<LocationData> allStats = new ArrayList<>();

    public List<LocationData> getAll() {
        return allStats;
    }

    @Override
    public List<LocationData> getByCountries(String country) {
        return allStats.stream()
                .filter(c->c.getCountry().equals(country))
                .collect(Collectors.toList());
    }

    @PostConstruct
    public void getVirusData() throws IOException, InterruptedException {
        List<LocationData> newStats = new ArrayList<>();

        ResponseEntity<String> response = restTemplate
                .exchange(baseURL,
                        HttpMethod.GET,
                        null, String.class);
        StringReader csvBodyReader = new StringReader(response.getBody());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for (CSVRecord record : records) {
            LocationData locationStat = new LocationData();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            locationStat.setLatestTotalCases(latestCases);
            locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
            newStats.add(locationStat);
        }
        this.allStats = newStats;

    }
}
