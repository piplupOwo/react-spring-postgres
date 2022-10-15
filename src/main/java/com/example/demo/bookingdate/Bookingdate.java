package com.example.demo.bookingdate;
import com.example.demo.loanpass.Loanpass;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
public class Bookingdate {
    @Id
    private String date;
    private String waitingList; //comma separated string of userId



    public Bookingdate() {
    }

    public Bookingdate(String date, String waitingList) {
        this.date = date;
        this.waitingList = waitingList;
    }


    public String getDate() {
        return date;
    }

    public String getWaitingList() {
        return waitingList;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setWaitingList(String waitingList) {
        this.waitingList = waitingList;
    }

    @Override
    public String toString() {
        return "Bookingdate{" +
                "date='" + date + '\'' +
                ", waitingList='" + waitingList + '\'' +
                '}';
    }




}
