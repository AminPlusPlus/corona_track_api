package com.amin.corona_track_api.controller;

import com.amin.corona_track_api.model.LocationData;
import com.amin.corona_track_api.service.MainDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainControllerApi {

    @Autowired
    MainDataService mainDataService;

    @ApiOperation("Get All Stats")
    @RequestMapping("/")
    public List<LocationData> getAll(){
        return mainDataService.getAll();
    }

    @ApiOperation("Search by country")
    @RequestMapping("/getCountry/{country}")
    public List<LocationData> getByCountry(@PathVariable String country){
        return mainDataService.getByCountries(country);
    }
}
