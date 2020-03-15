package com.amin.corona_track_api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {
    @Value("${app.version")
    private String appVersion;

    @GetMapping("/")
    public Map welcomePage(){
        Map<String,String> mapAppVersion = new HashMap<>();
        mapAppVersion.put("App Version",appVersion);
        return mapAppVersion;
    }
}
