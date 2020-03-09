package com.amin.corona_track_api.controller;

import com.amin.corona_track_api.model.LocationData;
import com.amin.corona_track_api.service.MailService;
import com.amin.corona_track_api.service.MainDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation(value = "Get All Stats",httpMethod = "GET")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<LocationData> getAll(){
        return mainDataService.getAll();
    }

    @ApiOperation(value = "Search by country")
    @RequestMapping(value = "/getCountry/{country}",method = RequestMethod.GET)
    public List<LocationData> getByCountry(@PathVariable String country){
        return mainDataService.getByCountries(country);
    }

    @RequestMapping("/sendMail")
    public String sendMail() {
        mailService.sendEmail("Testing from Spring Boot","<h1>Hello body</h1>","manodammaninson@gmail.com","amin030296@gmail.com");
        return "done";
    }
}
