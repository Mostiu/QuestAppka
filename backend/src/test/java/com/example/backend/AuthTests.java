package com.example.backend;

import com.example.backend.auth.AuthenticationRequest;
import com.example.backend.auth.AuthenticationResponse;
import com.example.backend.auth.AuthenticationService;
import com.example.backend.auth.RegisterRequest;
import com.example.backend.config.JwtService;
import com.example.backend.user.App_User;
import com.example.backend.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void testRegister() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .password("password123")
                .build();

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("hashedPassword");
        Mockito.when(jwtService.generateToken(Mockito.any())).thenReturn("mockedToken");

        AuthenticationResponse response = authenticationService.register(registerRequest);

        assertEquals("mockedToken", response.getToken());
        // You can add more assertions based on your specific requirements
    }

    @Test
    void testAuthenticate() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("john.doe@example.com")
                .password("password123")
                .build();

        App_User user = new App_User("John Doe", "john.doe@example.com", "hashedPassword");
        Mockito.when(userRepository.findUserByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(jwtService.generateToken(Mockito.any())).thenReturn("mockedToken");

        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

        assertEquals("mockedToken", response.getToken());

    }

    @Test
    void testAuthenticateUserNotFound() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("nonexistent.user@example.com")
                .password("password123")
                .build();

        Mockito.when(userRepository.findUserByEmail(Mockito.anyString())).thenReturn(Optional.empty());

       /* assertThrows(, () -> authenticationService.authenticate(authenticationRequest));*/

    }
}
