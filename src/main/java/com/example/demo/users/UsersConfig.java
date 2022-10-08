package com.example.demo.users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration

public class UsersConfig {
    @Bean
    CommandLineRunner commandLineRunner(UsersRepository repository){


        return args -> {
            Users mariam = new Users(
                    1L,
                    "Mariam",
                    "mariam.jamm@gmail.com",
                    86507732,
                    "Admin",
                    "password"

            );

            Users saitama = new Users(
                    2L,
                    "Saitama",
                    "saitama.jamm@gmail.com",
                    86507732,
                    "Admin",
                    "password"

            );

            Users naruto = new Users(
                    3L,
                    "Naruto",
                    "naruto.jamm@gmail.com",
                    86507732,
                    "Admin",
                    "password"

            );

            repository.saveAll(
                    List.of(mariam, saitama, naruto)
            );

        };
    }
}
