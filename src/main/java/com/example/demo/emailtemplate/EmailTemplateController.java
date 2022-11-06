package com.example.demo.emailtemplate;

import com.example.demo.loanpass.Loanpass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/emailtemplates")

public class EmailTemplateController {
    private final EmailTemplateService emailTemplateService;
    @Autowired
    public EmailTemplateController(EmailTemplateService emailTemplateService){
        this.emailTemplateService = emailTemplateService;
    }
    @GetMapping
    public List<EmailTemplate> getEmailTemplates(){
        return emailTemplateService.getEmailTemplates();
    }

    @GetMapping(path="/get/{emailTemplateId}")
    public Optional<EmailTemplate> getEmailTemplateById(@PathVariable("emailTemplateId") Integer emailTemplateId){
        return emailTemplateService.getEmailTemplateById(emailTemplateId);
    }

    @PostMapping
    public void addEmailTemplate(@RequestBody EmailTemplate emailTemplate) {
        emailTemplateService.addNewEmailTemplate(emailTemplate);
    }

    @DeleteMapping(path="{emailTemplateId}")
    public void deleteEmailTemplate(@PathVariable("emailTemplateId") Integer emailTemplateId){
        emailTemplateService.deleteEmailTemplate(emailTemplateId);
    }

    @PutMapping(path="{emailTemplateId}")
    public void updateEmailTemplate(
            @PathVariable("emailTemplateId") Integer emailTemplateId,
            @RequestBody EmailTemplate emailTemplate
    ){
        System.out.println(emailTemplateId);
        String emailTemplateName = emailTemplate.getEmailTemplateName();
        String emailTemplateBody = emailTemplate.getEmailTemplateBody();
        emailTemplateService.updateEmailRepository(emailTemplateId,emailTemplateName,emailTemplateBody);
    }

}
