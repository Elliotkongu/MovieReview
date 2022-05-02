package com.example.moviereview.dtos;

import lombok.Value;

@Value
public class RegistrationDTO {
    String username;
    String password;
    String role;
}
