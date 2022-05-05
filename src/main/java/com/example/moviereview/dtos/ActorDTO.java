package com.example.moviereview.dtos;

import lombok.Value;

@Value
public class ActorDTO {
    Long id;
    Long userId;
    String firstName;
    String surName;
    String dateOfBirth;
    String gender;
}
