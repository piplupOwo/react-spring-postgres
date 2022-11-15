package com.example.demo.successloan;
import java.time.LocalDateTime;  // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;  // Import the DateTimeFormatter class
import com.example.demo.attractions.Attractions;
import com.example.demo.attractions.AttractionsRepository;
import com.example.demo.attractions.AttractionsService;
import com.example.demo.emailsender.EmailSenderService;
import com.example.demo.emailtemplate.EmailTemplate;
import com.example.demo.emailtemplate.EmailTemplateService;
import com.example.demo.loanpass.Loanpass;
import com.example.demo.loanpass.LoanpassRepository;
import com.example.demo.users.UserRepo;
import com.example.demo.users.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuccessloanService {
    private final SuccessloanRepository successloanRepository;
    private final UserService userService;
    private final EmailTemplateService emailTemplateService;
    private final EmailSenderService emailSenderService;
    private final AttractionsService attractionsService;
    private final UserRepo userRepo;
    private final LoanpassRepository loanpassRepository;
    private final AttractionsRepository attractionsRepository;

    public SuccessloanService(SuccessloanRepository successloanRepository,
                              UserService userService, EmailTemplateService emailTemplateService, EmailSenderService emailSenderService,
                              AttractionsService attractionsService, UserRepo userRepo, LoanpassRepository loanpassRepository, AttractionsRepository attractionsRepository) {
        this.successloanRepository = successloanRepository;
        this.userService = userService;
        this.emailTemplateService = emailTemplateService;
        this.emailSenderService = emailSenderService;
        this.attractionsService = attractionsService;
        this.userRepo = userRepo;
        this.loanpassRepository = loanpassRepository;
        this.attractionsRepository = attractionsRepository;
    }

    public List<Successloan> getSuccessloan() {
        return successloanRepository.findAll();
    }

    public List<Successloan> getSuccessloanByAttractionId(Integer id) {
        return successloanRepository.findSuccessloanByAttractionId(id);
    }

    public List<Successloan> getSuccessloanByAttractionIdAndMonthAndYear(Integer id, String month, String year) {
        return successloanRepository.findSuccessloanByAttractionIdAndMonthAndYear(id, month, year);
    }
    public List<Successloan> getSuccessloanByAttractionIdAndMonthAndYearAndDay(Integer id, String month, String year, String day) {
        return successloanRepository.findSuccessloanByAttractionIdAndMonthAndYearAndDay(id, month, year, day);
    }
    public List<Successloan> getSuccessloanByStaffIdandAttractionIdAndMonthAndYearAndDay(Integer id, Integer attractionId, String month, String year, String day) {
        return successloanRepository.findSuccessloanByStaffIdAndAttractionIdAndMonthAndYearAndDay(id, attractionId, month, year, day);
    }

    public List<Successloan> getSuccessloanByStaffId(Integer id) {
        return successloanRepository.findSuccessloanByStaffId(id);
    }

    public List<Successloan> getSuccessloanByStaffIdAndMonthAndYear(Integer id, String month, String year) {
        return successloanRepository.findSuccessloanByStaffIdAndMonthAndYear(id, month, year);
    }



    public void addNewSuccessloan(Successloan successloan) {
        Optional<Successloan> successloanOptional = successloanRepository.findSuccessloanBySuccessLoanId(successloan.getSuccessLoanId());
        if (successloanOptional.isPresent()) {
            throw new IllegalStateException("Successloan ID taken");
        }
        successloanRepository.save(successloan);
        System.out.println("CREATED");




        Attractions selectedAttraction = attractionsRepository.getById(successloan.getAttractionId());

        Optional<EmailTemplate> currentTemplate = emailTemplateService.getEmailTemplateById(selectedAttraction.getEmailTemplateID());
        EmailTemplate defaultTemplate = new EmailTemplate();
        if(!currentTemplate.equals(null)){
             defaultTemplate = currentTemplate.get() ;
        }
        else{
            // check template
            defaultTemplate.setEmailTemplateName("Loanpass Confirmation");
            defaultTemplate.setEmailTemplateBody("<p>Dear #borrowerName#, </p><p>We are pleased to inform that your booking to #attractionName# is confirmed as follows:</p><p>Date of Visit : #dateOfVisit# (1 day only) </p><p>Membership ID : #corpPassNo#</p><p>For any change in visit date, you are required to cancel your booking (at least 1 day before) </p><p>and to submit a new booking in the system. </p><p>Attached is the Corporate Membership letter to #attractionName#. Please check that the details are accurate. </p><p>Please take note of the following on the day of your visit to #attractionName# :</p><p>Present this email, the attached corporate membership letter and your staff pass at the entrance of #attractionName# </p><p>Entry is strictly based on your details in this email and corporate membership letter. </p><ul><li>Your presence is required on the day of visit. Entry will be denied without staff’s presence. </li><li>Your booking is non-transferable. Entry is strictly based on the details in this email and Corporate Membership letter. </li><li>Visit date is strictly based on the date stated in this email and Corporate Membership letter. </li><li>Staff found abusing the Corporate Membership letter will be subject to disciplinary actions.</li><li>Enjoy your visit to &nbsp;#attractionName#</li></ul><p>Warm regards,</p><p>HR Department&nbsp;</p><p><br></p>\n");

        }

        // formatting
        DateTimeFormatter dateFormatObject = DateTimeFormatter.ofPattern("E, MMM dd yyyy");


        String templateTitle = defaultTemplate.getEmailTemplateName();
        String templateBody = defaultTemplate.getEmailTemplateBody();

        String recipient;
        String recipientEmail;
        String attractionName = selectedAttraction.getName();
        String corpPassNo;

        Optional<AppUser> user = userRepo.findById(Long.valueOf(successloan.getStaffId()));
        String userString = user.toString();

        String username = userString.substring(userString.indexOf("username=") + 9, userString.indexOf(", email="));
        String email = userString.substring(userString.indexOf("email=") + 6, userString.indexOf(", password="));

        // count the number of successloan on the same day
        List<Successloan> successloanList = getSuccessloanByAttractionIdAndMonthAndYearAndDay(successloan.getAttractionId(), successloan.getMonth(), successloan.getYear(), successloan.getDay());
        int count = successloanList.size();
        Loanpass loanpass = loanpassRepository.findLoanPassByAttractionId(successloan.getAttractionId()).get(count-1);

        recipient = username;
        recipientEmail = email;
        corpPassNo = String.valueOf(loanpass.getPassNumber());


        // supposed to get attraction
        // get userID

        LocalDateTime visitDate = LocalDateTime.of(Integer.parseInt(successloan.getYear()),Integer.parseInt(successloan.getMonth())
                ,Integer.parseInt(successloan.getDay()),0,0);

        String visitDateInString = visitDate.format(dateFormatObject);
        // regex patterns
        templateBody = templateBody.replace("#borrowerName#",recipient);
        templateBody = templateBody.replace("#attractionName#",attractionName);
        templateBody = templateBody.replace("#corpPassNo#",corpPassNo);
        templateBody = templateBody.replace("#dateOfVisit#",visitDateInString);
        templateBody = templateBody.replace("#visitDate#",visitDateInString);
        emailSenderService.sendEmail(recipientEmail,templateTitle,templateBody);
        System.out.println("EMAIL SENT");

        String attachmentLink = loanpass.getDescription().split(",./")[4];
        System.out.println(attachmentLink);
        if (attachmentLink == null || attachmentLink.equals("")){
            System.out.println("NO ATTACHMENT");
        }
        else{
            emailSenderService.sendEmailWithAttachment(recipientEmail,templateTitle + "'s ATTACHMENT","Attachment",attachmentLink);
            System.out.println("EMAIL SENT WITH ATTACHMENT");
        }
    }



    public void deleteSuccessloan(Integer successloanId) {
        boolean exists = successloanRepository.existsById(successloanId);
        if (!exists) {
            throw new IllegalStateException("Successloan with ID " + successloanId + " does not exist");
        }
        successloanRepository.deleteById(successloanId);
    }



}
