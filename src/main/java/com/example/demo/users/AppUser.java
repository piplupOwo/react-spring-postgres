package com.example.demo.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class AppUser {
    @Id @GeneratedValue(strategy = AUTO)
    private Long id;
    //    private String name;
    private String username;
    private String email;
    private String password;
    public String phoneNumber;
    private boolean isEnabled = false;
    @ManyToMany(fetch = EAGER)
    private Collection<UserRole> userRoles = new ArrayList<>();
    private String confirmationToken = UUID.randomUUID().toString();

    
    public String getConfirmationToken(){
        return confirmationToken;
    }
    public void setConfirmationToken(String confirmationToken){
        this.confirmationToken = confirmationToken;
    }
    public boolean getEnabled(){
        return isEnabled;
    }
    public void setEnabled(boolean variable){
        this.isEnabled = variable;
    }
}