package com.example.moviereview.controllers;

import com.example.moviereview.dtos.LoginDTO;
import com.example.moviereview.dtos.RegistrationDTO;
import com.example.moviereview.services.RegistrationAndLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api")
public class RegistrationAndLoginController {

    private final RegistrationAndLoginService registrationAndLoginService;

    @Autowired
    public RegistrationAndLoginController(RegistrationAndLoginService registrationAndLoginService) {
        this.registrationAndLoginService = registrationAndLoginService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        return registrationAndLoginService.registerUser(registrationDTO);
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        return registrationAndLoginService.loginUser(loginDTO);
    }
}
