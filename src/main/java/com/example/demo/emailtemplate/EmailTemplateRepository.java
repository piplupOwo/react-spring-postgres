package com.example.demo.emailtemplate;
import com.example.demo.loanpass.Loanpass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate,Integer>{
    @Query("SELECT s FROM EmailTemplate s WHERE s.emailTemplateId = ?1")
    Optional<EmailTemplate> getEmailTempalteById(Integer passId);

}
