package com.example.demo.successloan;

import javax.persistence.*;

@Entity
@Table
public class Successloan {

    @Id
    @SequenceGenerator(
            name="successloan_sequence",
            sequenceName ="successloan_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "successloan_sequence"
    )

    private Integer successLoanId;
    private Integer staffId;
    private Integer attractionId;
    private String month;
    private String year;
    private String day;


    public Successloan() {
    }

    public Successloan(Integer successLoanId, Integer staffId, Integer attractionId, String month, String year, String day) {
        this.successLoanId = successLoanId;
        this.staffId = staffId;
        this.attractionId = attractionId;
        this.month = month;
        this.year = year;
        this.day = day;
    }

    public Integer getSuccessLoanId() {
        return successLoanId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public Integer getAttractionId() {
        return attractionId;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
    public String getDay() {
        return day;
    }

    public void setSuccessLoanId(Integer successLoanId) {
        this.successLoanId = successLoanId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public void setAttractionId(Integer attractionId) {
        this.attractionId = attractionId;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }
    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Successloan{" +
                "successLoanId=" + successLoanId +
                ", staffId=" + staffId +
                ", attractionId=" + attractionId +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
