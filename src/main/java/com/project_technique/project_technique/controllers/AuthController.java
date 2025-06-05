package com.project_technique.project_technique.controllers;


import com.project_technique.project_technique.dto.AuthRequest;
import com.project_technique.project_technique.dto.AuthResponse;
import com.project_technique.project_technique.security.CustomUserDetails;
import com.project_technique.project_technique.services.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );


        final CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(request.username());
        final String jwt = jwtUtil.generateToken(userDetails);
        final String role = userDetails.getEmploye().getRole().name();

        return ResponseEntity.ok(new AuthResponse(jwt, role));
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(Map.of(
                "email", userDetails.getUsername(),
                "role", userDetails.getEmploye().getRole().name(),
                "firstName", userDetails.getEmploye().getFirstName(),
                "lastName", userDetails.getEmploye().getLastName()
        ));
    }

}
