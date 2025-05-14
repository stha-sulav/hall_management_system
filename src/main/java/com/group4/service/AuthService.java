package com.group4.service;

import com.group4.model.UserModel;
import com.group4.lib.enums.UserRole;
import com.group4.lib.data.SimpleFileORM;
import com.group4.App;

import java.util.Optional;

public class AuthService {

    private UserService userService;
    private SimpleFileORM<UserModel> userORM;

    public AuthService(UserService userService) {
        this.userService = userService;
        this.userORM = App.getUserORM();
    }

    public boolean authenticate(String usernameOrEmail, String password) {
        Optional<UserModel> user = userORM
                .find(u -> (usernameOrEmail.equalsIgnoreCase(u.getUsername())
                        || usernameOrEmail.equalsIgnoreCase(u.getEmail())) &&
                        password.equals(u.getPassword()))
                .stream().findFirst();
        return user.isPresent();
    }

    public boolean authorize(String username, UserRole role) {
        Optional<UserModel> user = userService.findUser(u -> u.getUsername().equals(username));
        if (user.isPresent()) {
            return user.get().getRole() == role;
        }
        return false;
    }

    public boolean signup(String username, String email, String password, String firstname, String lastname,
            String photo, String phoneNumber, String address) {
        UserModel newUser = new UserModel(username, email, password, UserRole.Customer, firstname, lastname, photo,
                phoneNumber, address);
        return userService.createUser(newUser) != null;
    }

    public UserRole getUserRole(String usernameOrEmail) {
        Optional<UserModel> user = userORM
                .find(u -> (usernameOrEmail.equalsIgnoreCase(u.getUsername())
                        || usernameOrEmail.equalsIgnoreCase(u.getEmail())))
                .stream().findFirst();
        return user.map(UserModel::getRole).orElse(null);
    }
}
