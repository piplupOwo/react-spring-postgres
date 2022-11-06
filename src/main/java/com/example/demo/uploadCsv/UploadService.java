package com.example.demo.uploadCsv;

import com.example.demo.users.AppUser;
import com.example.demo.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UploadService {

    private final UserService userService;

    public Boolean addUser(AppUser user){
        try{
            userService.saveUser(user);
        }catch(IllegalStateException msg){
            return false;
        }

        return true;
    }


}
