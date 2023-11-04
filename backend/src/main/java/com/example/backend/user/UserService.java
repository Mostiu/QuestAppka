package com.example.backend.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User hello() {
        return new User(1L, "John", "mail@gmail.com", "1234");
    }
}
