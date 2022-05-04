package com.example.moviereview.dtos;

import lombok.Value;

import java.util.List;

public class MovieDTO {

    @Value
    public static class Create {
        List<Long> actorIds;
        Long directorId;
    }
}
