package com.example.demo.successloan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuccessloanRepository extends JpaRepository<Successloan, Integer> {
    @Query("SELECT s FROM Successloan s WHERE s.successLoanId = ?1")
    Optional<Successloan> findSuccessloanBySuccessLoanId(Integer successLoanId);

    @Query("SELECT s FROM Successloan s WHERE s.attractionId = ?1")
    List<Successloan> findSuccessloanByAttractionId(Integer attractionId);

    @Query("SELECT s FROM Successloan s WHERE s.attractionId = ?1 AND s.month = ?2 AND s.year = ?3")
    List<Successloan> findSuccessloanByAttractionIdAndMonthAndYear(Integer attractionId, String month, String year);
    @Query("SELECT s FROM Successloan s WHERE s.attractionId = ?1 AND s.month = ?2 AND s.year = ?3 AND s.day = ?4")
    List<Successloan> findSuccessloanByAttractionIdAndMonthAndYearAndDay(Integer attractionId, String month, String year, String day);

    @Query("SELECT s FROM Successloan s WHERE s.staffId = ?1")
    List<Successloan> findSuccessloanByStaffId(Integer id);

    @Query("SELECT s FROM Successloan s WHERE s.staffId = ?1 AND s.month = ?2 AND s.year = ?3")
    List<Successloan> findSuccessloanByStaffIdAndMonthAndYear(Integer id, String month, String year);
    @Query("SELECT s FROM Successloan s WHERE s.staffId = ?1 AND s.attractionId = ?2 AND s.month = ?3 AND s.year = ?4 AND s.day = ?5")
    List<Successloan> findSuccessloanByStaffIdAndAttractionIdAndMonthAndYearAndDay(Integer id, Integer attractionId, String month, String year, String day);
}
