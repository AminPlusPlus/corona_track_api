package com.amin.corona_track_api.config;

import com.moesif.servlet.MoesifFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;

@Configuration
@Import(SwaggerConfig.class)
public class AppConfig {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Filter moesifFilter() {
        return new MoesifFilter("eyJhcHAiOiIzNDU6NDQ4IiwidmVyIjoiMi4wIiwib3JnIjoiODg6MjEzIiwiaWF0IjoxNTgzOTcxMjAwfQ.SjxLJq8Qb8WgwBssPXh47SwPTO-2R8dvtm8-pGdiiLQ");
    }
}
