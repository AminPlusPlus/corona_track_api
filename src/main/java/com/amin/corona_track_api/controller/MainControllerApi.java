package com.amin.corona_track_api.controller;

import com.amin.corona_track_api.model.LocationData;
import com.amin.corona_track_api.service.MailService;
import com.amin.corona_track_api.service.MainDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainControllerApi {

    @Autowired
    MainDataService mainDataService;
    @Autowired
    MailService mailService;

    @Value("${build.version}")
    private String versionApp;

    @RequestMapping("/")
    public String versionApp(){
     return  versionApp;
    }

    @ApiOperation(value = "Get All Stats")
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<LocationData> getAll(){
        return mainDataService.getAll();
    }

    @ApiOperation(value = "Search by country")
    @RequestMapping(value = "/getCountry/{country}",method = RequestMethod.GET)
    public List<LocationData> getByCountry(@PathVariable String country){
        return mainDataService.getByCountries(country);
    }

}
