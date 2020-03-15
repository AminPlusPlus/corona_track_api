package com.amin.corona_track_api.controller;

import com.amin.corona_track_api.model.LocationData;
import com.amin.corona_track_api.service.VirusDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/confirms")
public class ConfirmsController {


    VirusDataService virusDataService;

    public ConfirmsController( @Autowired VirusDataService virusDataService,
                               @Value("${covid19.url.confirms}") String url ) {
        virusDataService.setBaseURl(url);
        this.virusDataService = virusDataService;
    }

    @ApiOperation(value = "Get All Stats")
    @GetMapping
    public List<LocationData> getAll(){
        return virusDataService.getAll();
    }

    @ApiOperation(value = "Search by country")
    @GetMapping(value = "/getCountry/{country}")
    public List<LocationData> getByCountry(@PathVariable String country){
        return virusDataService.getByCountries(country);
    }

    @ApiOperation(value = "Search by state")
    @GetMapping(value = "/getState/{state}")
    public List<LocationData> getByState(@PathVariable String state){
        return virusDataService.getByState(state);
    }

    @ApiOperation(value = "Get Total Confirms")
    @GetMapping(value = "/total")
    public Map<String, String> getTotalConfirms(){
        return virusDataService.getTotal();
    }

}
