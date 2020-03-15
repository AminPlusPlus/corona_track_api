package com.amin.corona_track_api.controller;

import com.amin.corona_track_api.model.LocationData;
import com.amin.corona_track_api.service.VirusDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/recovers")
public class RecoversController {

    @Autowired
    @Qualifier(value = "recoversServiceImpl")
    VirusDataService virusDataService;


    @ApiOperation(value = "Get All Stats")
    @GetMapping
    public List<LocationData> getAll(){
        return virusDataService.getAll();
    }

    @ApiOperation(value = "Search by country")
    @RequestMapping(value = "/getCountry/{country}",method = RequestMethod.GET)
    public List<LocationData> getByCountry(@PathVariable String country){
        return virusDataService.getByCountries(country);
    }

    @ApiOperation(value = "Search by state")
    @RequestMapping(value = "/getState/{state}",method = RequestMethod.GET)
    public List<LocationData> getByState(@PathVariable String state){
        return virusDataService.getByState(state);
    }

    @ApiOperation(value = "Get Total Recovers")
    @RequestMapping(value = "/total",method = RequestMethod.GET)
    public Map<String, String> getTotalConfirms(){
        return virusDataService.getTotal();
    }

}
