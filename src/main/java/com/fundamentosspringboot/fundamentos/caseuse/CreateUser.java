package com.fundamentosspringboot.fundamentos.caseuse;

import com.fundamentosspringboot.fundamentos.entity.User;
import com.fundamentosspringboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {

    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser){
        return userService.save(newUser);
    }

}
