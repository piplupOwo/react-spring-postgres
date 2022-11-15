package com.example.demo.attractionSettings;

import com.example.demo.attractions.Attractions;

import javax.persistence.*;

@Entity
@Table
public class Settings {
    @Id
    @SequenceGenerator(
            name="setting_sequence",
            sequenceName ="setting_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "setting_sequence"
    )
    private Integer settingID;
    private Integer maxPassPerLoan;

    public Settings(){

    }

    public Settings(Integer settingID, Integer maxPassPerLoan) {
        this.settingID = settingID;
        this.maxPassPerLoan = maxPassPerLoan;
    }

    public Integer getSettingID() {
        return settingID;
    }


    public Integer getMaxPassPerLoan() {
        return maxPassPerLoan;
    }

    public void setMaxPassPerLoan(Integer maxPassPerLoan) {
        this.maxPassPerLoan = maxPassPerLoan;
    }
}
