package com.amin.corona_track_api.service;

import com.amin.corona_track_api.model.LocationData;
import java.util.List;
import java.util.Map;

public interface VirusDataService {

    void setBaseURl(String url);
    List<LocationData> getAll();
    List<LocationData> getByCountries(String country);
    List<LocationData> getByState(String state);
    Map<String,String> getTotal();
}
