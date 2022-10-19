package com.example.demo.emailsender;

import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("oopproject712@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("MAIL SENT");
    }


    public int sendEmailWithAttachment(String toEmail, String subject, String body, String pathToAttachment){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try{
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom("oopproject712@gmail.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);


            UrlResource file = new UrlResource(pathToAttachment);
            mimeMessageHelper.addAttachment(file.getFilename(), file);
//            ATTACHMENT FILE NAME IS LIKE A RENAME OF THE FILE

            mailSender.send(mimeMessage);
            System.out.println("MAIL SENT");
            return 1;
        } catch (MessagingException | MalformedURLException e){
            throw new IllegalStateException("Failed to send email");
        }


    }

}
