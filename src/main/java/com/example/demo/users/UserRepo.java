package com.example.demo.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser findByConfirmationToken(String code);
    
    Optional<AppUser> findById(Long id);
    Optional<AppUser> findByEmail(String email);
    

}
