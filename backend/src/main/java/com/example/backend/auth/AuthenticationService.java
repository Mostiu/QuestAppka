package com.example.backend.auth;

import com.example.backend.config.JwtService;
import com.example.backend.user.App_User;
import com.example.backend.user.UserRepository;
import com.example.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        if(userRepository.findUserByEmail(request.getEmail()).isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        App_User user = new App_User(request.getFirstname(), request.getLastname(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        userService.addUserToCityChallenges(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        App_User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(()-> new IllegalStateException("User not found"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new IllegalStateException("Wrong password");
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
