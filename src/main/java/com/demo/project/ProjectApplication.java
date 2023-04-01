package com.demo.project;

import com.demo.project.dto.EmailEvent;
import com.demo.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProjectApplication {

    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }


    /**
     * Send mail.
     *
     * @param emailEvent the email event
     */
    @EventListener()
    public void sendMail(EmailEvent emailEvent) {
        System.out.println(emailEvent.getBody());
        emailSenderService.sendEmail(emailEvent.getToEmail(), emailEvent.getSubject(), emailEvent.getBody());
    }

}
