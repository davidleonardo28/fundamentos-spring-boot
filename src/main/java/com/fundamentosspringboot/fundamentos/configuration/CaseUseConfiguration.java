package com.fundamentosspringboot.fundamentos.configuration;

import com.fundamentosspringboot.fundamentos.caseuse.GetUser;
import com.fundamentosspringboot.fundamentos.caseuse.GetUserImplement;
import com.fundamentosspringboot.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
