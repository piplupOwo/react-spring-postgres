package com.example.demo.loanpass;
import com.example.demo.bookingdate.Bookingdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Loanpass {
    @Id
    @SequenceGenerator(
            name="loanpass_sequence",
            sequenceName ="loanpass_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loanpass_sequence"
    )

    private Integer passId;
    private Integer attractionId;
    private Integer passNumber;
    private Integer previousLoanBy;
    private String description;


    public Loanpass() {
    }

    public Loanpass(Integer passId, Integer attractionId, Integer passNumber, Integer previousLoanBy, String description) {
        this.passId = passId;
        this.attractionId = attractionId;
        this.passNumber = passNumber;
        this.previousLoanBy = previousLoanBy;
        this.description = description;
    }

    public Integer getPassId() {
        return passId;
    }

    public Integer getAttractionId() {
        return attractionId;
    }

    public Integer getPassNumber() {
        return passNumber;
    }

    public Integer getPreviousLoanBy() {
        return previousLoanBy;
    }

    public String getDescription() {
        return description;
    }

    public void setPassId(Integer passId) {
        this.passId = passId;
    }

    public void setAttractionId(Integer attractionId) {
        this.attractionId = attractionId;
    }

    public void setPassNumber(Integer passNumber) {
        this.passNumber = passNumber;
    }

    public void setPreviousLoanBy(Integer previousLoanBy) {
        this.previousLoanBy = previousLoanBy;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LoanPass{" +
                "passId=" + passId +
                ", attractionId=" + attractionId +
                ", passNumber=" + passNumber +
                ", previousLoanBy=" + previousLoanBy +
                ", description='" + description + '\'' +
                '}';
    }
}
