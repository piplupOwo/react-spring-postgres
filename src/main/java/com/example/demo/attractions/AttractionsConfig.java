package com.example.demo.attractions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AttractionsConfig {
    @Bean
    CommandLineRunner commandLineRunnerAttraction(AttractionsRepository repository){
    return args -> {
            Attractions zoo = new Attractions(
                   1L,
                    "Zoo",
                    "Zoo is a really fun place. You get to see the white tiger",
                    "Physical",
                    50,
                    "This is a great eamil template. You are warned",
                    100,
                    "Active"

            );

            Attractions universalStudios = new Attractions(
                    2L,
                    "Universal Studios",
                    "Universal Studios is an excellent place. You can find theme-parks here, and good food.",
                    "E-pass",
                    50,
                    "This is a great eamil template. You are warned",
                    100,
                    "Active"

            );

            Attractions fortCanning = new Attractions(
                    3L,
                    "Fort Canning Park",
                    "Ever wondered what the Bristish and the Japanese did to Singapore? Yes? Alright come here!",
                    "Both",
                    50,
                    "This is a great eamil template. You are warned",
                    100,
                    "Non-active"

            );

        repository.saveAll(
                List.of(zoo, universalStudios, fortCanning)
        );
        };
    };
}
