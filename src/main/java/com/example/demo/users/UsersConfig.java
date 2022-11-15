package com.example.demo.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class UsersConfig {
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new UserRole(null, "ROLE_USER"));
            userService.saveRole(new UserRole(null, "ROLE_ADMIN"));
            userService.saveRole(new UserRole(null, "ROLE_OFFICER"));

            userService.saveUser(new AppUser(null, "Siang Meng", "brianlimjj@gmail.com", "12345678", "12345678", new ArrayList<>() ));
            userService.saveUser(new AppUser(null, "John", "johm@gmail.com", "12345678", "12345678", new ArrayList<>() ));
            userService.saveUser(new AppUser(null, "Ted", "ted@gmail.com", "12345678", "12345678", new ArrayList<>() ));


            userService.addRoleToUser("Siang Meng", "ROLE_USER");
            userService.addRoleToUser("John", "ROLE_ADMIN");
            userService.addRoleToUser("Ted", "ROLE_OFFICER");

        };
    }
}
