package com.example.moviereview.dtos;

import lombok.Value;

@Value
public class JwtDTO {
    String token;
    Long userId;
    String role;
}
