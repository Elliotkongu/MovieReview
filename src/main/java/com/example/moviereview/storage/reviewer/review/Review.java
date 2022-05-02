package com.example.moviereview.storage.reviewer.review;

import com.example.moviereview.storage.movie.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private String title;
    private String content;
    private String reviewer;
}
