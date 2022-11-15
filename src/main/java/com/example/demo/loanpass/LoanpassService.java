package com.example.demo.loanpass;

import com.example.demo.attractions.Attractions;
import com.example.demo.attractions.AttractionsRepository;
import com.example.demo.attractions.AttractionsService;
import com.example.demo.emailtemplate.EmailTemplateService;
import com.example.demo.successloan.Successloan;
import com.example.demo.users.AppUser;
import com.example.demo.users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.emailsender.EmailSenderService;
import com.example.demo.emailtemplate.EmailTemplate;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoanpassService {
    private final LoanpassRepository loanPassRepository;
    private final UserRepo userRepo;
    private final EmailSenderService emailSenderService;
    private final EmailTemplateService emailTemplateService;

    @Autowired
    public LoanpassService(LoanpassRepository loanPassRepository,UserRepo userRepo,EmailSenderService emailSenderService, EmailTemplateService emailTemplateService){
        this.loanPassRepository = loanPassRepository;
        this.userRepo = userRepo;
        this.emailSenderService = emailSenderService;
        this.emailTemplateService = emailTemplateService;
    }

    public List<Loanpass> getLoanPass(){
        return loanPassRepository.findAllLoanPassOrdered();
    }

    public List<Loanpass> getLoanPassByAttractionId(Integer attractionId){
        return loanPassRepository.findLoanPassByAttractionId(attractionId);
    }

    public void addNewLoanPass(Loanpass loanPass){
        Optional<Loanpass> loanPassOptional = loanPassRepository.findLoanPassBypassId(loanPass.getPassId());
        if(loanPassOptional.isPresent()){
            throw new IllegalStateException("passId taken");
        }
        loanPassRepository.save(loanPass);
    }

    public void deleteLoanPass(int loanPassId){
        boolean exists = loanPassRepository.existsById(loanPassId);
        if(!exists){
            throw new IllegalStateException("loanPass with id " + loanPassId + " does not exists");
        }
        loanPassRepository.deleteById(loanPassId);
    }

    public void collectCardReminder(){
        // get all the successloans
        ArrayList<Loanpass> allLoans = (ArrayList<Loanpass>)loanPassRepository.findAll();
        for(Loanpass loanPass: allLoans){
            try{
                // filter their status --> check if unloaned --> if Uncollected then add to arraylist
                String[] splittedString = loanPass.getDescription().split(",./");
                String status = splittedString[0];
                String passType = splittedString[1];
                System.out.println("Status :"+status);
                if(status.equals("Uncollected")){
                    // send email
                    AppUser loanedBy = userRepo.getById((long)loanPass.getPreviousLoanBy());
                    if(!loanedBy.equals(null) && passType.equals("Physical")){
                        //  name of last loaned by, name of attraction, card ID
                        String borrowerName = loanedBy.getUsername();
                        EmailTemplate sendTemplate = new EmailTemplate();
                        sendTemplate.setEmailTemplateName("Reminder to Collect Pass");
                        sendTemplate.setEmailTemplateBody("<p>Dear #borrowerName#,</p><p><br></p>" +
                                "<p>Please remember to collect your pass (Card ID: ##cardID#). " +
                                "Please enjoy your trip.</p><p><br></p><p>Regards,</p><p>HR Department</p>");

                        String templateTitle = sendTemplate.getEmailTemplateName();
                        String templateBody = sendTemplate.getEmailTemplateBody();

                        templateBody = templateBody.replace("#borrowerName#",borrowerName);
                        templateBody = templateBody.replace("#cardID#",loanPass.getPassNumber().toString());
                        emailSenderService.sendEmail(loanedBy.getEmail(),templateTitle,templateBody);
                    }
                }
            }
            catch (Exception e){
                continue;
            }
        }
    }

    public void returnCardReminder(){
        ArrayList<Loanpass> allLoans = (ArrayList<Loanpass>)loanPassRepository.findAll();
        for(Loanpass loanPass: allLoans){
            try{
                // filter their status --> check if unloaned --> if Uncollected then add to arraylist
                String[] splittedString = loanPass.getDescription().split(",./");
                String status = splittedString[0];
                String passType = splittedString[1];
                System.out.println("Status :"+status);
                if(status.equals("Loaned out") && passType.equals("Physical")){
                    // send email
                    AppUser loanedBy = userRepo.getById((long)loanPass.getPreviousLoanBy());
                    if(!loanedBy.equals(null)){
                        //  name of last loaned by, name of attraction, card ID
                        String borrowerName = loanedBy.getUsername();
                        EmailTemplate sendTemplate = new EmailTemplate();
                        sendTemplate.setEmailTemplateName("Reminder to Return Pass");
                        sendTemplate.setEmailTemplateBody("<p>Dear #borrowerName#,</p><p><br></p>" +
                                "<p>Please remember to return your pass (Card ID: ##cardID#). " +
                                "Please enjoy your trip.</p><p><br></p><p>Regards,</p><p>HR Department</p>");

                        String templateTitle = sendTemplate.getEmailTemplateName();
                        String templateBody = sendTemplate.getEmailTemplateBody();

                        templateBody = templateBody.replace("#borrowerName#",borrowerName);
                        templateBody = templateBody.replace("#cardID#",loanPass.getPassNumber().toString());
                        emailSenderService.sendEmail(loanedBy.getEmail(),templateTitle,templateBody);
                    }
                }
            }
            catch (Exception e){
                continue;
            }
        }

    }

    @Transactional
    public void updateLoanPass(int passId, int attractionId, int passNumber, int previousLoanBy, String description)
    {
        Loanpass loanPass = loanPassRepository.findById(passId)
                .orElseThrow(() -> new IllegalStateException(
                        "loanPass with id " + passId + "does not exist"
                ));
        if (attractionId != 0 && !Objects.equals(loanPass.getAttractionId(), attractionId)){
            loanPass.setAttractionId(attractionId);
        }
        if (passNumber != 0 && !Objects.equals(loanPass.getPassNumber(), passNumber)){
            loanPass.setPassNumber(passNumber);
        }
        if (previousLoanBy != 0 && !Objects.equals(loanPass.getPreviousLoanBy(), previousLoanBy)){
            loanPass.setPreviousLoanBy(previousLoanBy);
        }
        if (description != null && description.length() > 0 && !Objects.equals(loanPass.getDescription(), description)){
            loanPass.setDescription(description);
        }
        // split
        String[] splittedString = loanPass.getDescription().split(",./");
        AppUser loanedBy = userRepo.getById((long)previousLoanBy);
        System.out.println((loanedBy));
        String status = splittedString[0];
        System.out.println("status "+splittedString[0]);
        String replacementFee = splittedString[2];
        System.out.println("replacementFee "+splittedString[2]);

        // check if expired
        if(status.equals("Lost")){
            EmailTemplate defaultTemplate = new EmailTemplate();
            defaultTemplate.setEmailTemplateName("Loan Pass Lost");
            defaultTemplate.setEmailTemplateBody("<p>Dear #borrowerName# ,</p><p>It seems that your Loan Pass with the ID #loanPassId# has been lost. " +
                    "Please pay the replacement fee $#replacementFee# .</p><p><br></p><p>Regards,</p><p>HR Department</p>");
            // formatting
            String templateTitle = defaultTemplate.getEmailTemplateName();
            String templateBody = defaultTemplate.getEmailTemplateBody();

            String recipient = loanedBy.getUsername();
            String recipientEmail = loanedBy.getEmail();

            // regex patterns
            templateBody = templateBody.replace("#borrowerName#",recipient);
            templateBody = templateBody.replace("#loanPassId#",String.valueOf(passId));
            templateBody = templateBody.replace("#replacementFee#",replacementFee);
            emailSenderService.sendEmail(recipientEmail,templateTitle,templateBody);
        }
        // everyday 9am --> go through waiting list and send

        // check if collected
        if(status.equals("Loaned out")){
            System.out.println("loaned?? "+status);
            EmailTemplate defaultTemplate = new EmailTemplate();
            defaultTemplate.setEmailTemplateName("Loan Pass Collected");
            defaultTemplate.setEmailTemplateBody("<p>Dear #borrowerName#," +
                    "</p><p>You have collected your pass (ID: #loanPassID#) " +
                    "as of #collectedDate# .</p><p>No reply is required. This is an auto-generated email.</p><p><br></p><p>Regards,</p><p>HR Department</p>");

            // formatting
            String templateTitle = defaultTemplate.getEmailTemplateName();
            String templateBody = defaultTemplate.getEmailTemplateBody();

            String recipient = loanedBy.getUsername();
            String recipientEmail = loanedBy.getEmail();

            // tag replacements
            templateBody = templateBody.replace("#borrowerName#",recipient);
            templateBody = templateBody.replace("#loanPassID#",String.valueOf(passId));

            //date
            LocalDateTime currDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");
            String formattedDate = formatter.format(currDate);
            templateBody = templateBody.replace("#collectedDate#",formattedDate);

            emailSenderService.sendEmail(recipientEmail,templateTitle,templateBody);
        }

        // check if returned
        if(status.equals("Uncollected")){
            EmailTemplate defaultTemplate = new EmailTemplate();
            defaultTemplate.setEmailTemplateName("Loan Pass Returned");
            defaultTemplate.setEmailTemplateBody("<p>Dear #borrowerName#,</p><p><br></p><p>Thank you for returning your pass. </p>" +
                    "<p>Hope you have enjoyed your visit. We are looking forward to your future visits.</p><p><br></p><p>Regards,</p><p>HR Department</p>\n");
            // formatting
            String templateTitle = defaultTemplate.getEmailTemplateName();
            String templateBody = defaultTemplate.getEmailTemplateBody();

            String recipient = loanedBy.getUsername();
            String recipientEmail = loanedBy.getEmail();

            // regex patterns
            templateBody = templateBody.replace("#borrowerName#",recipient);
            emailSenderService.sendEmail(recipientEmail,templateTitle,templateBody);
        }
    }



}
