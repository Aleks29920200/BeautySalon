package com.example.beautySalon.services;


import com.example.beautySalon.domain.dto.view.RoleViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class EmailService {
        public final JavaMailSender emailSender;
        private UserServiceImpl userService;

        @Autowired
        public EmailService(@Qualifier("getJavaMailSender") JavaMailSender emailSender, UserServiceImpl userService) {
            this.emailSender = emailSender;
            this.userService = userService;
        }



    public void sendSimpleMessage(String to, String subject, String text) {
        String emailOfTheBoss = getEmailOfTheBoss();
        SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailOfTheBoss);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        }

    private String getEmailOfTheBoss() {
        String emailOfTheBoss = this.userService.allUsers().
                stream().filter(e-> e.getAuthorities().
                        stream().toList().get(0).getAuthority().
                        hashCode()=="BOSS".hashCode()).
                findFirst().get().getEmail();
        return emailOfTheBoss;
    }
}
