package com.javatechie.jwt.api.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import com.javatechie.jwt.api.entity.EmailModel;
import com.javatechie.jwt.api.repository.EmailRepository;
import com.javatechie.jwt.api.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository repo;

    @RequestMapping(value = "/email", method = RequestMethod.POST, consumes = "application/json")
    public String sendEmail(@RequestBody EmailModel emailModel) {
        LOGGER.info("Sending email");

        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo("dop3madhu@gmail.com");
            helper.setReplyTo(emailModel.getEmail());
            helper.setFrom(emailModel.getEmail());
            helper.setSubject(emailModel.getSubject());
            helper.setSentDate(jwtUtil.currentDate());
            helper.setText("From: " + emailModel.getUsername() + "\n" + emailModel.getMessage());
            emailModel.setSendDate(jwtUtil.currentDate());
            repo.save(emailModel);
        } catch (MessagingException e) {
            LOGGER.error("Failed to send email: " + emailModel.toString(), e);
        } finally {}
        javaMailSender.send(mail);

        return "success";
    }

    @RequestMapping(value = "/emails", method = RequestMethod.GET)
    public List<EmailModel> getemail() {
       return  repo.findAll();
    }
}