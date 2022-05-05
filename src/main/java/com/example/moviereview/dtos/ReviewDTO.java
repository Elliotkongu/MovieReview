package com.example.moviereview.dtos;

import lombok.Value;

public class ReviewDTO {

    @Value
    public static class Create {
        Long movieId;
        String title;
        String content;
        Long reviewerId;
    }
}
