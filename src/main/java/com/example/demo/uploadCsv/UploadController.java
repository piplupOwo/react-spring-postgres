package com.example.demo.uploadCsv;

import com.example.demo.users.AppUser;
import com.example.demo.users.UserRole;
import com.example.demo.users.UserService;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.common.record.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/upload")
public class UploadController {

    private final UploadService service;
    private final UserService userService;
    @Autowired
    public UploadController(UploadService service,UserService userService){ this.service = service;this.userService=userService;}

    @PostMapping
    public String uploadCSV(@RequestParam("file") MultipartFile file) throws Exception{
        List<AppUser> userList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);

        parseAllRecords.forEach(record ->{
            AppUser user = new AppUser();
            user.setPassword("defaultPassword");
            user.setEmail(record.getString("Email"));
            user.setUserRoles(new ArrayList<>());
            user.setUsername(record.getString("Name"));
            try{
                user.setUsername(record.getString("Phone"));
            }catch(IllegalArgumentException e){
                user.setPhoneNumber("");
            };
            userList.add(user);

        });
        String addedUser = "";
        String skippedUser = "";
        for(AppUser u : userList){
            if(service.addUser(u)){
                addedUser += "Added User " + u.getUsername() + "\n";
            }else{
                skippedUser += "Skipped User " + u.getUsername() + "\n";
            }
        }
        System.out.println(addedUser + skippedUser);
        return addedUser+skippedUser;
    }
}
