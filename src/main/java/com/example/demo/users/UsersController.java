package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {
    private final UsersService  usersService;

    @Autowired
    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getUsers(){
        return usersService.getUsers();
    }

    @PostMapping
    public void registerNewUsers(@RequestBody Users users){
        usersService.addNewUsers(users);
    }

    @DeleteMapping(path = "{usersId}")
    public void deleteUsers(@PathVariable("usersId") Long userId){
        usersService.deleteUsers(userId);
    }

    @PutMapping(path = "{usersId}")
    public void updateUsers(
            @PathVariable("usersId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        usersService.updateUsers(studentId, name, email);
    }
}