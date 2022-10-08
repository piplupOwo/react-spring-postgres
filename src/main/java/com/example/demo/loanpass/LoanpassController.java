package com.example.demo.loanpass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/loanpass")
public class LoanpassController {
    private final LoanpassService loanPassService;

    @Autowired
    public LoanpassController(LoanpassService loanPassService) {
        this.loanPassService = loanPassService;
    }

    @GetMapping
    public List<Loanpass> getLoanPass() {
        return loanPassService.getLoanPass();
    }

    @PostMapping
    public void registerNewLoanPass(@RequestBody Loanpass loanPass) {
        loanPassService.addNewLoanPass(loanPass);
    }

    @DeleteMapping(path = "{loanPassId}")
    public void deleteLoanPass(@PathVariable("loanPassId") Integer loanPassId) {
        loanPassService.deleteLoanPass(loanPassId);
    }

    @PutMapping(path = "{loanPassId}")
    public void updateLoanPass(
            @PathVariable("loanPassId") Integer loanPassId,
            @RequestParam(required = false) Integer attractionId,
            @RequestParam(required = false) Integer passNumber,
            @RequestParam(required = false) Integer previousLoanBy,
            @RequestParam(required = false) String description) {
        loanPassService.updateLoanPass(loanPassId, attractionId, passNumber, previousLoanBy, description);
    }

}

