package com.example.moviereview.controllers;

import com.example.moviereview.dtos.MovieDTO;
import com.example.moviereview.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add_movie")
    public ResponseEntity<?> addMovie(@RequestBody MovieDTO.Create movieDTO) {
        return movieService.addMovie(movieDTO);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMovie(@PathVariable("id") Long id) {
        return movieService.getMovie(id);
    }

    @GetMapping("/get_by_actor/{actorId}")
    public ResponseEntity<?> getByActor(@PathVariable("actorId") Long actorId) {
        return movieService.getAllMoviesByActor(actorId);
    }

    @GetMapping("/get_by_director/{directorId}")
    public ResponseEntity<?> getByDirector(@PathVariable("directorId") Long directorId) {
        return movieService.getAllByDirector(directorId);
    }
}
