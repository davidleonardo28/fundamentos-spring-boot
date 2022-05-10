package com.fundamentosspringboot.fundamentos.caseuse;

import com.fundamentosspringboot.fundamentos.entity.User;
import com.fundamentosspringboot.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser{

    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
