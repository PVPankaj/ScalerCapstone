package com.scaler.capstone.jobportal.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendOtpEmail(String to, String name, String otp) {
        try {
            Context context = new Context();
            context.setVariable("name", name);
            context.setVariable("otp", otp);

            String body = templateEngine.process("otp-template", context);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Your OTP Code");
            helper.setText(body, true);

            mailSender.send(message);
        } catch (Exception e) {
            // handle exception
        }
    }
}