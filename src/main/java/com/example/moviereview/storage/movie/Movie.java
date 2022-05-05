package com.example.moviereview.storage.movie;

import com.example.moviereview.storage.actor.Actor;
import com.example.moviereview.storage.director.Director;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Actor> actors;
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
}
