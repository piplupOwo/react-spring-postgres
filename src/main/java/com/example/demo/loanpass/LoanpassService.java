package com.example.demo.loanpass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoanpassService {
    private final LoanpassRepository loanPassRepository;

    @Autowired
    public LoanpassService(LoanpassRepository loanPassRepository){
        this.loanPassRepository = loanPassRepository;
    }

    public List<Loanpass> getLoanPass(){
        return loanPassRepository.findAllLoanPassOrdered();
    }

    public List<Loanpass> getLoanPassByAttractionId(Integer attractionId){
        return loanPassRepository.findLoanPassByAttractionId(attractionId);
    }

    public void addNewLoanPass(Loanpass loanPass){
        Optional<Loanpass> loanPassOptional = loanPassRepository.findLoanPassBypassId(loanPass.getPassId());
        if(loanPassOptional.isPresent()){
            throw new IllegalStateException("passId taken");
        }
        loanPassRepository.save(loanPass);
    }

    public void deleteLoanPass(int loanPassId){
        boolean exists = loanPassRepository.existsById(loanPassId);
        if(!exists){
            throw new IllegalStateException("loanPass with id " + loanPassId + " does not exists");
        }
        loanPassRepository.deleteById(loanPassId);
    }

    @Transactional
    public void updateLoanPass(int passId, int attractionId, int passNumber, int previousLoanBy, String description)
    {
        Loanpass loanPass = loanPassRepository.findById(passId)
                .orElseThrow(() -> new IllegalStateException(
                        "loanPass with id " + passId + "does not exist"
                ));
        if (attractionId != 0 && !Objects.equals(loanPass.getAttractionId(), attractionId)){
            loanPass.setAttractionId(attractionId);
        }
        if (passNumber != 0 && !Objects.equals(loanPass.getPassNumber(), passNumber)){
            loanPass.setPassNumber(passNumber);
        }
        if (previousLoanBy != 0 && !Objects.equals(loanPass.getPreviousLoanBy(), previousLoanBy)){
            loanPass.setPreviousLoanBy(previousLoanBy);
        }
        if (description != null && description.length() > 0 && !Objects.equals(loanPass.getDescription(), description)){
            loanPass.setDescription(description);
        }
    }



}
