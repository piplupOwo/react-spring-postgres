package com.example.demo.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<UserRole, Long> {
    UserRole findByName(String name);
}
