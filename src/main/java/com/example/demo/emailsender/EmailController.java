package com.example.demo.emailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/sendemail")
public class EmailController {

    @Autowired
    private EmailSenderService senderService;

    @GetMapping
    public String sendEmail() {
        try {
            senderService.sendEmail("adam334123@gmail.com", "subject", "body");
            return "Email sent";
        } catch (Exception e) {
            return "Error in sending email: " + e;
        }

    }

    @PostMapping
    public String sendEmail(@RequestBody Email email) {
        if (email.getPathToAttachment() == null) {
            try {
                senderService.sendEmail(email.getTo(), email.getSubject(), email.getBody());
                return "Email sent without attachment";
            } catch (Exception e) {
                return "Error in sending email without attachment: " + e;
            }
        } else {
            try {
                String[] toEmails = email.getTo().split(",");
                String subject = email.getSubject();
                String body = email.getBody();
                String pathToAttachment = email.getPathToAttachment();
                for (String toemail : toEmails) {
                    senderService.sendEmailWithAttachment(toemail, subject, body, pathToAttachment);
                }
                return "Email sent with attachment";

            } catch (Exception e) {
                return "Error in sending email with attachment: " + e;
            }
        }

    }






}
