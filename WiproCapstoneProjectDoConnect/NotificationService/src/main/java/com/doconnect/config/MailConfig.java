package com.doconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration // Marks this as a Spring configuration class
public class MailConfig {

    @Bean // Bean definition for JavaMailSender
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Configure Gmail SMTP settings
        mailSender.setHost("smtp.gmail.com"); // SMTP host
        mailSender.setPort(587); // TLS port
        mailSender.setUsername("manikantathota569@gmail.com"); // Sender email
        mailSender.setPassword("sxud fvva pigd wabz"); // App password (not Gmail login password)

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp"); // Protocol
        props.put("mail.smtp.auth", "true"); // Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS (TLS encryption)
        props.put("mail.debug", "true"); // Enable debug logs

        return mailSender; // Return configured mail sender
    }
}
