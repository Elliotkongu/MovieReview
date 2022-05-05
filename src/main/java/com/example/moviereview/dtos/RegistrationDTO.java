package com.example.moviereview.dtos;

import lombok.Value;

@Value
public class RegistrationDTO {
    String role;
    String firstName;
    String surName;
    String dateOfBirth;
    String gender;
}
