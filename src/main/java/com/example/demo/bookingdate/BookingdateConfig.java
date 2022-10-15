package com.example.demo.bookingdate;

import com.example.demo.loanpass.Loanpass;
import com.example.demo.loanpass.LoanpassRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//String date, int passId, String waitingList
@Configuration
public class BookingdateConfig {

    @Bean
    CommandLineRunner commandLineRunnerBookingDate(BookingdateRepository repository) {
        return args -> {
            Bookingdate bookingdate1 = new Bookingdate(
                    "1,16,10,2022",
                    "1,2,3"
            );

            Bookingdate bookingdate2 = new Bookingdate(
                    "2,16,10,2022",
                    "3,2,1"
            );

            Bookingdate bookingdate3 = new Bookingdate(
                    "3,16,10,2022",
                    "2,1,3"
            );

            repository.saveAll(
                    List.of(bookingdate1, bookingdate2, bookingdate3)
            );
        };
    }
}
