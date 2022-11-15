
package com.example.demo.emailtemplate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class EmailTemplateConfig {
    @Bean
    CommandLineRunner commandLineRunnerEmailTemplate(EmailTemplateRepository repository) {
        return args -> {
            EmailTemplate ePassConfirmationTemplate = new EmailTemplate();
            ePassConfirmationTemplate.setEmailTemplateName("Confirmation by loanpass");
            ePassConfirmationTemplate.setEmailTemplateBody("<p>Dear #borrowerName#,</p><p><br></p><p>We are pleased to inform that your booking to #attractionName# is confirmed as follows:</p><p>Date of Visit : #visitDate# (1 day only)</p><p>Membership ID : #corpPassNo#</p><p><br></p><p>For any change in visit date, you are required to cancel your booking (at least 1 day before)</p><p>and to submit a new booking in the system.</p><p>Attached is the Corporate Membership letter to #attractionName#. Please check that the details are accurate.</p><p>Please take note of the following on the day of your visit to #attractionName# :</p><p>Present this email, the attached corporate membership letter and your staff pass at the entrance of #attractionName#</p><p>Entry is strictly based on your details in this email and corporate membership letter.</p><ul><li>Your presence is required on the day of visit. Entry will be denied without staff’s presence.</li><li>Your booking is non-transferable. Entry is strictly based on the details in this email and Corporate Membership letter.</li><li>Visit date is strictly based on the date stated in this email and Corporate Membership letter.</li><li>Staff found abusing the Corporate Membership letter will be subject to disciplinary actions.</li><li>Enjoy your visit to&nbsp;#attractionName#</li><li><br></li></ul><p>Warm regards,</p><p>HR Department&nbsp;</p>");
            EmailTemplate physicalCardConformationTemplate = new EmailTemplate();
            physicalCardConformationTemplate.setEmailTemplateName("Confirmation by physical cards");
            physicalCardConformationTemplate.setEmailTemplateBody("<p>Dear #borrowerName#, </p><p> We are pleased to inform that your booking to #attractionName# is confirmed as follows: </p><p>Date of Visit : Saturday, 12 February 2022 (1 day only) Membership ID : #corpPassNo#</p><p>For any change in visit date, you are required to cancel your booking (at least 1 day before) and to submit a new booking in the system. </p><p>Attached is the authorisation letter to #attractionName# . Please check that the details are accurate. </p><p>Please take note of the following for your visit to #attractionName# : </p><ul><li>Present this email confirmation to the General Office to collect the membership card(s). </li><li>Collect the membership card(s) from the General Office one day before your visit date and return the membership card(s) by 9am the next working day after your visit. </li><li>Present the membership card(s), the authorisation letter and your staff pass at the entrance of #attractionName#</li><li>Entry is strictly based on the membership card(s) and the authorisation letter. </li><li>Your presence is required on the day of visit. Entry will be denied without staff’s </li><li>presence. </li><li>Your booking is non-transferable. </li><li>Visit date is strictly based on the date stated in this email and the authorisation letter. </li><li>Staff found abusing the membership(s) will be subject to disciplinary actions. </li></ul><p>Enjoy your visit to #attractionName#!</p><p>Warm regards </p><p>HR Department&nbsp;</p><p><br></p>");

            repository.save(ePassConfirmationTemplate);
            repository.save(physicalCardConformationTemplate);
        };
    }
}


