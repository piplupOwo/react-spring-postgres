package com.example.demo.users;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.*;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
