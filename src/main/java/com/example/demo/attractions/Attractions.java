package com.example.demo.attractions;

import jdk.jfr.Description;

import javax.persistence.*;

@Entity
@Table
public class Attractions {
    @Id
    @SequenceGenerator(
            name="attractions_sequence",
            sequenceName ="attractions_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "attractions_sequence"
    )
    private Integer attractionID;
    private String name;
    private String description;
    private String passType;
    private Integer replacementFee;
    private Integer emailTemplateID;
    private Integer totalPasses;
    private String status;

    public Attractions(){

    }

    public Attractions(Integer attractionID, String name, String description, String passType, Integer replacementFee, Integer emailTemplateID, Integer totalPasses, String status) {
        this.attractionID = attractionID;
        this.name = name;
        this.description = description;
        this.passType = passType;
        this.replacementFee = replacementFee;
        this.emailTemplateID = emailTemplateID;
        this.totalPasses = totalPasses;
        this.status = status;
    }

    public Integer getAttractionID() {
        return attractionID;
    }

    public void setAttractionID(Integer attractionID) {
        this.attractionID = attractionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassType() {
        return passType;
    }

    public void setPassType(String passType) {
        this.passType = passType;
    }

    public Integer getReplacementFee() {
        return replacementFee;
    }

    public void setReplacementFee(Integer replacementFee) {
        this.replacementFee = replacementFee;
    }

    public Integer getEmailTemplateID() {
        return emailTemplateID;
    }

    public void setEmailTemplateID(Integer emailTemplateID) {
        this.emailTemplateID = emailTemplateID;
    }

    public Integer getTotalPasses() {
        return totalPasses;
    }

    public void setTotalPasses(Integer totalPasses) {
        this.totalPasses = totalPasses;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
