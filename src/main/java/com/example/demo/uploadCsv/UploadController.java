package com.example.demo.uploadCsv;

import com.example.demo.users.AppUser;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.common.record.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/upload")
public class UploadController {

    private final UploadService service;

    @Autowired
    public UploadController(UploadService service){ this.service = service;}

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
            user.setUsername(record.getString("Name"));
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
        return addedUser + skippedUser;
    }
}
