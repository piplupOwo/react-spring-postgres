package com.example.demo.loanpass;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanpassRepository extends JpaRepository<Loanpass, Integer> {
    @Query("SELECT s FROM Loanpass s WHERE s.passId = ?1")
    Optional<Loanpass> findLoanPassBypassId(Integer passId);
}
