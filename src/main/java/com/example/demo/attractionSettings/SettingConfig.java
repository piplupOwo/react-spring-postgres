package com.example.demo.attractionSettings;

import com.example.demo.attractions.Attractions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SettingConfig {
    @Bean
    CommandLineRunner commandLineRunnerSettings(SettingRepository repository){
        return args -> {
          Settings setting = new Settings(1,2);
          repository.saveAll(
                  List.of(setting)
          );
        };
    }
}
