package com.example.demo.attractionSettings;

import com.example.demo.attractions.Attractions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SettingService {
    private final SettingRepository settingRepository;

    @Autowired
    public SettingService(SettingRepository settingRepository){
        this.settingRepository = settingRepository;
    }

    public List<Settings> getSettings(){
        return settingRepository.findAll();
    }


    @Transactional
    public void updateSetting(int settingID, int maxPassPerLoan){
        Settings setting = settingRepository.findSettingsBySettingID(settingID)
                .orElseThrow(()->new IllegalStateException(
                        "Setting with id" + settingID + "Does not exist"
                ));
        if(maxPassPerLoan != setting.getMaxPassPerLoan() && maxPassPerLoan > 0){
            setting.setMaxPassPerLoan(maxPassPerLoan);
        }
    }
}
