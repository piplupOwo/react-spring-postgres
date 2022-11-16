package com.example.demo.attractionSettings;

import com.example.demo.loanpass.Loanpass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/settings")
public class SettingController {

    private final SettingService settingService;

    @Autowired
    public SettingController(SettingService settingService){
        this.settingService = settingService;
    }

    @GetMapping
    public List<Settings> getSettings() {
        return settingService.getSettings();
    }
    @PutMapping
    public String updateLoanPass(
            @RequestParam("settingID") Integer settingID,
            @RequestParam("maxPassPerLoan") Integer maxPassPerLoan
    ){
        settingService.updateSetting(settingID,maxPassPerLoan);
        System.out.println("Data receive " + settingID + " " + maxPassPerLoan);
        return "Data receive " + settingID + " " + maxPassPerLoan;
    }
}
