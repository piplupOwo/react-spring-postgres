package com.example.demo.users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    AppUser saveUser(AppUser user);

    // AppUser registerUser(AppUser user);
    UserRole saveRole(UserRole role);

    void addRoleToUser(String username, String roleName);

    AppUser getUser(String username);

    AppUser getReferenceById(Long id);

    void deleteUser(Long id);

    List<AppUser> getUsers();

    boolean verify(String confirmationToken);
}
