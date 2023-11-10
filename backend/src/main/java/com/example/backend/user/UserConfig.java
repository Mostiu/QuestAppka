package com.example.backend.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository repository){
        return args -> {
            App_User user1 = new App_User("John", "mail@gmail.com", "1234");
            App_User user2 = new App_User( "Johnathan", "mail1@gmail.com", "12342");
            repository.saveAll(List.of(user1, user2));
        };
    }
}
