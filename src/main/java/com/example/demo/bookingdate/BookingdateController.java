package com.example.demo.bookingdate;

import com.example.demo.loanpass.Loanpass;
import com.example.demo.loanpass.LoanpassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/bookingdate")
public class BookingdateController {
    private final BookingdateService bookingdateService;

    @Autowired
    public BookingdateController(BookingdateService bookingdateService) {
        this.bookingdateService = bookingdateService;
    }

    @GetMapping
    public List<Bookingdate> getBookingdate() {
        return bookingdateService.getBookingDate();
    }

    @GetMapping(path = "{date}")
    public Optional<Bookingdate> getBookingdateByDate(@PathVariable("date") String date) {
        return bookingdateService.getSpecificBookingDate(date);
    }

    @PostMapping
    public void registerNewBookingdate(@RequestBody Bookingdate bookingdate) {
        bookingdateService.addNewBookingDate(bookingdate);
    }



    @PutMapping(path = "{date}")
    public void updateBookingdate(
            @PathVariable("date") String date,
            @RequestParam(required = false) String waitingList) {
        bookingdateService.updateBookingDate(date, waitingList);

    }



}

