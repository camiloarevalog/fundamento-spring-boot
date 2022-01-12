package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.caseuse.GetUser;
import com.fundamentos.springboot.fundamentos.caseuse.getUserImplement;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new getUserImplement(userService);
    }
}
