package com.linktic.ecommerce.backend.infrastructure.controller;

import com.linktic.ecommerce.backend.application.RegistrationService;
import com.linktic.ecommerce.backend.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/security")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private  final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody User user){
        log.info("Clave encriptada: {}", passwordEncoder.encode(user.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(registrationService.register(user), HttpStatus.CREATED);
    }
}
