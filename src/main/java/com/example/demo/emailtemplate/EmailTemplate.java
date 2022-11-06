package com.example.demo.emailtemplate;
import javax.persistence.*;

@Entity
@Table
public class EmailTemplate  {
    @Id
    @SequenceGenerator(
            name="emailtemplate_sequence",
            sequenceName ="emailtemplate_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "emailtemplate_sequence"
    )
    private Integer emailTemplateId;
    private String emailTemplateName;
    @Column(columnDefinition="TEXT")
    private String emailTemplateBody;

    public EmailTemplate(){

    }
    public EmailTemplate(Integer emailTemplateId, String emailTemplateName, String emailTemplateBody){
        this.emailTemplateId = emailTemplateId;
        this.emailTemplateName = emailTemplateName;
        this.emailTemplateBody = emailTemplateBody;
    }

    public Integer getEmailTemplateId() {
        return emailTemplateId;
    }

    public String getEmailTemplateName() {
        return emailTemplateName;
    }

    public String getEmailTemplateBody() {
        return emailTemplateBody;
    }

    public void setEmailTemplateId(Integer emailTemplateId) {
        this.emailTemplateId = emailTemplateId;
    }

    public void setEmailTemplateName(String emailTemplateName) {
        this.emailTemplateName = emailTemplateName;
    }

    public void setEmailTemplateBody(String emailTemplateBody) {
        this.emailTemplateBody = emailTemplateBody;
    }
}
