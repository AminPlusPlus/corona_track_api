package com.amin.corona_track_api.service;

import com.amin.corona_track_api.model.LocationData;

import java.util.List;

public interface MainDataService {
    List<LocationData> getAll();
    List<LocationData> getByCountries(String country);
}
