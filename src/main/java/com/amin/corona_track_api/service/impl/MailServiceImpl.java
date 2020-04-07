package com.amin.corona_track_api.service.impl;

import com.amin.corona_track_api.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String subject, String body, String... to) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);

        msg.setSubject(subject);
        msg.setText(body);

        javaMailSender.send(msg);
    }
}
