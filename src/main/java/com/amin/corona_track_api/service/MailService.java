package com.amin.corona_track_api.service;

public interface MailService {
     void sendEmail( String subject, String body,String... to );

}
