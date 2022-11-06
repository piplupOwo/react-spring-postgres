package com.example.demo.users;



import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
//    AppUser registerUser(AppUser user);
    UserRole saveRole(UserRole role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    void deleteUser(Long id);

    List<AppUser> getUsers();
}
