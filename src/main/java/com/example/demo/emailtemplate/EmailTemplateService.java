package com.example.demo.emailtemplate;
import com.example.demo.loanpass.Loanpass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmailTemplateService {
    private final EmailTemplateRepository emailTemplateRepository;

    @Autowired
    public EmailTemplateService(EmailTemplateRepository emailTemplateRepository){
        this.emailTemplateRepository = emailTemplateRepository;
    }

    public List<EmailTemplate> getEmailTemplates(){
        return emailTemplateRepository.findAll();
    }

    public Optional<EmailTemplate> getEmailTemplateById(Integer emailTemplateId){
        return emailTemplateRepository.getEmailTemplateById(emailTemplateId);
    }

    public void addNewEmailTemplate(EmailTemplate emailTemplate){
//        Optional<EmailTemplate> emailTemplateOptional = emailTemplateRepository.findEmailTemplateById(emailTemplate.getEmailTemplateId());
//        if(emailTemplateOptional.isPresent()){
//            throw new IllegalStateException()
//        }
        emailTemplateRepository.save(emailTemplate);
    }
    public void deleteEmailTemplate(int emailTemplateId){
        boolean exists = emailTemplateRepository.existsById(emailTemplateId);
        if(!exists){
            throw new IllegalStateException("Email Template with id " + emailTemplateId + " does not exists");
        }
        emailTemplateRepository.deleteById(emailTemplateId);
    }

    @Transactional
    public void updateEmailRepository(int emailTemplateId, String emailTemplateName, String emailTemplateBody)
    {
        EmailTemplate emailTemplate = emailTemplateRepository.findById(emailTemplateId)
                .orElseThrow(() -> new IllegalStateException(
                        "Email Template with id " + emailTemplateId + "does not exist"
                ));
        if (emailTemplateName != null && emailTemplateName.length() > 0 && !Objects.equals(emailTemplate.getEmailTemplateName(), emailTemplateName)){
            emailTemplate.setEmailTemplateName(emailTemplateName);
        }
        if (emailTemplateBody != null && emailTemplateBody.length() > 0 && !Objects.equals(emailTemplate.getEmailTemplateBody(), emailTemplateBody)){
            emailTemplate.setEmailTemplateBody(emailTemplateBody);
        }
    }
}
