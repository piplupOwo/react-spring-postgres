package com.example.demo.bookingdate;

import com.example.demo.loanpass.Loanpass;
import com.example.demo.loanpass.LoanpassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookingdateService {
    private final BookingdateRepository bookingdateRepository;


    @Autowired
    public BookingdateService(BookingdateRepository bookingdateRepository, LoanpassRepository loanPassRepository){
        this.bookingdateRepository = bookingdateRepository;
    }

    public List<Bookingdate> getBookingDate(){
        return bookingdateRepository.findAll();
    }

    public Optional<Bookingdate> getSpecificBookingDate(String date){
        return bookingdateRepository.findBookingDate(date);
    }

    public void addNewBookingDate(Bookingdate bookingdate){
        Optional<Bookingdate> bookingdateOptional = bookingdateRepository.findBookingDate(bookingdate.getDate());
        if(bookingdateOptional.isPresent()){
            throw new IllegalStateException("date taken");
        }
        bookingdateRepository.save(bookingdate);
    }

    @Transactional
    public void updateBookingDate(String date, String waitingList) {
        Bookingdate bookingdate = bookingdateRepository.findBookingDate(date)
                .orElseThrow(() -> new IllegalStateException(
                        "bookingdate with date " + date + "does not exist"
                ));
        if (waitingList != null && waitingList.length() > 0 && !Objects.equals(bookingdate.getWaitingList(), waitingList)){
            bookingdate.setWaitingList(waitingList);
        }
    }




}
