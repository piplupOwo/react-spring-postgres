package com.example.demo.successloan;

import com.example.demo.loanpass.Loanpass;
import com.example.demo.loanpass.LoanpassRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SuccessloanConfig {

    @Bean
    CommandLineRunner commandLineRunnerSuccess(SuccessloanRepository repository) {
        return args -> {
            Successloan successloan1 = new Successloan(
                    1,
                    1,
                    1,
                    "10",
                    "2022",
                    "10"
            );

            Successloan successloan2 = new Successloan(
                    2,
                    2,
                    2,
                    "10",
                    "2022",
                    "10"
            );

            Successloan successloan3 = new Successloan(
                    3,
                    3,
                    3,
                    "10",
                    "2022",
                    "10"
            );

            repository.saveAll(
                    List.of(successloan1, successloan2, successloan3)
            );
        };
    }
}

