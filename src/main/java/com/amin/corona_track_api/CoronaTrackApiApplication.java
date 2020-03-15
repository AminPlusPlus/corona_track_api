package com.amin.corona_track_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CoronaTrackApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoronaTrackApiApplication.class, args);
    }

}
