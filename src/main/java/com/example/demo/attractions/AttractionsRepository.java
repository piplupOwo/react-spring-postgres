package com.example.demo.attractions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttractionsRepository extends JpaRepository<Attractions, Long> {
    @Query("SELECT s FROM Attractions s WHERE s.name = ?1")
    Optional<Attractions> findAttractionsByName(String Name);
}
