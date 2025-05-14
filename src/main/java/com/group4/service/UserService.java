package com.group4.service;

import com.group4.model.UserModel;
import com.group4.lib.data.SimpleFileORM;
import com.group4.App;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.List;

public class UserService {

    private SimpleFileORM<UserModel> userORM;

    public UserService() {
        this.userORM = App.getUserORM();
    }

    public UserModel createUser(UserModel user) {
        try {
            userORM.create(user);
            return user;
        } catch (SimpleFileORM.ORMException e) {
            System.err.println("ERROR: Failed to create user: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Optional<UserModel> findUser(Predicate<UserModel> predicate) {
        return userORM.find(predicate).stream().findFirst();
    }

    public List<UserModel> findAllUsers() {
        return userORM.findAll();
    }

    public boolean updateUser(UserModel user) {
        try {
            userORM.update(u -> u.getUsername().equals(user.getUsername()), u -> user);
            return true;
        } catch (SimpleFileORM.ORMException e) {
            System.err.println("ERROR: Failed to update user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(UserModel user) {
        try {
            userORM.delete(u -> u.getUsername().equals(user.getUsername()));
            return true;
        } catch (SimpleFileORM.ORMException e) {
            System.err.println("ERROR: Failed to delete user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
