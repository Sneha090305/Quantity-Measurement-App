package com.bridgelabz.service;

import com.bridgelabz.dto.AuthRequest;
import com.bridgelabz.dto.AuthResponse;
import com.bridgelabz.dto.RegisterRequest;
import com.bridgelabz.entity.Role;
import com.bridgelabz.entity.User;
import com.bridgelabz.repository.UserRepository;
import com.bridgelabz.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return new AuthResponse(jwtToken);
    }
}