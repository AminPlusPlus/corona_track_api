package com.amin.corona_track_api.service;

import com.amin.corona_track_api.model.LocationData;

import java.io.IOException;
import java.util.List;

public interface MainDataService {
    List<LocationData> getAllDada (String url) throws IOException;
}
