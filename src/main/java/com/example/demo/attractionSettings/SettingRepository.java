package com.example.demo.attractionSettings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
public interface SettingRepository extends JpaRepository<Settings,Long> {

    @Query("SELECT s FROM Settings s WHERE s.settingID=?1")
    Optional<Settings> findSettingsBySettingID(Integer SettingID);

}
