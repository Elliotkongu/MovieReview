package com.example.moviereview.controllers;

import com.example.moviereview.dtos.RegistrationDTO;
import com.example.moviereview.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api")
public class RegistrationAndLoginController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationAndLoginController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        return registrationService.registerUser(registrationDTO);
    }
}
