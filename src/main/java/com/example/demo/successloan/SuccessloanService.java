package com.example.demo.successloan;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SuccessloanService {
    private final SuccessloanRepository successloanRepository;

    public SuccessloanService(SuccessloanRepository successloanRepository) {
        this.successloanRepository = successloanRepository;
    }

    public List<Successloan> getSuccessloan() {
        return successloanRepository.findAll();
    }

    public List<Successloan> getSuccessloanByAttractionId(Integer id) {
        return successloanRepository.findSuccessloanByAttractionId(id);
    }

    public List<Successloan> getSuccessloanByAttractionIdAndMonthAndYear(Integer id, String month, String year) {
        return successloanRepository.findSuccessloanByAttractionIdAndMonthAndYear(id, month, year);
    }
    public List<Successloan> getSuccessloanByAttractionIdAndMonthAndYearAndDay(Integer id, String month, String year, String day) {
        return successloanRepository.findSuccessloanByAttractionIdAndMonthAndYearAndDay(id, month, year, day);
    }
    public List<Successloan> getSuccessloanByStaffIdandAttractionIdAndMonthAndYearAndDay(Integer id, Integer attractionId, String month, String year, String day) {
        return successloanRepository.findSuccessloanByStaffIdAndAttractionIdAndMonthAndYearAndDay(id, attractionId, month, year, day);
    }

    public List<Successloan> getSuccessloanByStaffId(Integer id) {
        return successloanRepository.findSuccessloanByStaffId(id);
    }

    public List<Successloan> getSuccessloanByStaffIdAndMonthAndYear(Integer id, String month, String year) {
        return successloanRepository.findSuccessloanByStaffIdAndMonthAndYear(id, month, year);
    }


    public void addNewSuccessloan(Successloan successloan) {
        Optional<Successloan> successloanOptional = successloanRepository.findSuccessloanBySuccessLoanId(successloan.getSuccessLoanId());
        if (successloanOptional.isPresent()) {
            throw new IllegalStateException("Successloan ID taken");
        }
        successloanRepository.save(successloan);
    }

    public void deleteSuccessloan(Integer successloanId) {
        boolean exists = successloanRepository.existsById(successloanId);
        if (!exists) {
            throw new IllegalStateException("Successloan with ID " + successloanId + " does not exist");
        }
        successloanRepository.deleteById(successloanId);
    }



}
