package com.example.demo.emailsender;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/sendemail")
public class EmailSenderController {
    private final EmailSenderService emailSenderService;

    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping
    public void sendEmailWithAttachment(@RequestBody String jsonRequest){

        System.out.println("Hello here");
        System.out.println(jsonRequest);

        String toEmail = jsonRequest.substring(jsonRequest.indexOf("\"to\"") + 7, jsonRequest.indexOf("\"subject\"") - 8);
        System.out.println(toEmail);
        String subject = jsonRequest.substring(jsonRequest.indexOf("\"subject\"") + 12, jsonRequest.indexOf("\"body\"") - 8);
        System.out.println(subject);
        String body = jsonRequest.substring(jsonRequest.indexOf("\"body\"") + 9, jsonRequest.indexOf("\"pathToAttachment\"") - 8);
        System.out.println(body);
        String pathToAttachment = jsonRequest.substring(jsonRequest.indexOf("\"pathToAttachment\"") + 20, jsonRequest.length() - 4);
        System.out.println(pathToAttachment);
        emailSenderService.sendEmailWithAttachment(toEmail, subject, body, pathToAttachment);
    }
}
