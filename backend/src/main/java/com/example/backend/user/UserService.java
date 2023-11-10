package com.example.backend.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository app_userRepository;
    @Autowired
    public UserService(UserRepository app_userRepository) {
        this.app_userRepository = app_userRepository;
    }

    public List<App_User> getUsers() {
        return app_userRepository.findAll();
    }

    public void addNewUser(App_User user) {
        Optional<App_User> userOptional = app_userRepository.findUserByEmail(user.getEmail());

        if(userOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        app_userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = app_userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("user with id " + userId + " does not exists");
        }
        app_userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email) {
        App_User user = app_userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exists"
        ));

        if(name != null && name.length() > 0 && !user.getName().equals(name)){
            user.setName(name);
        }

        if(email != null && email.length() > 0 && !user.getEmail().equals(email)){
            Optional<App_User> userOptional = app_userRepository.findUserByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            user.setEmail(email);
        }
    }
}
