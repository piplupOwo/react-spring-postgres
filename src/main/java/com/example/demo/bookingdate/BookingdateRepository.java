package com.example.demo.bookingdate;



import com.example.demo.loanpass.Loanpass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingdateRepository extends JpaRepository<Bookingdate, Integer> {
    @Query("SELECT s FROM Bookingdate s WHERE s.date = ?1")
    Optional<Bookingdate> findBookingDate(String date);
}
