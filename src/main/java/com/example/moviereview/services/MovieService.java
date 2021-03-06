package com.example.moviereview.services;

import com.example.moviereview.dtos.MovieDTO;
import com.example.moviereview.storage.actor.Actor;
import com.example.moviereview.storage.actor.ActorRepository;
import com.example.moviereview.storage.director.DirectorRepository;
import com.example.moviereview.storage.movie.Movie;
import com.example.moviereview.storage.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository,
                        DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
    }

    public ResponseEntity<?> addMovie(MovieDTO.Create movieDTO) {
        List<Actor> actorList = new ArrayList<>();
        for (Long id: movieDTO.getActorIds()) {
            if (actorRepository.findById(id).isPresent()) {
                actorList.add(actorRepository.findById(id).get());
            } else {
                return ResponseEntity.badRequest().body("Error: unable to find an actor with id " + id);
            }
        }
        if (directorRepository.findById(movieDTO.getDirectorId()).isEmpty()) {
            return ResponseEntity.badRequest().body("Director not found");
        }
        Movie movie = new Movie();
        movie.setActors(actorList);
        movie.setDirector(directorRepository.findById(movieDTO.getDirectorId()).get());
        movieRepository.save(movie);
        return ResponseEntity.ok().body("Movie saved");
    }

    public ResponseEntity<?> getAllMovies() {
        return ResponseEntity.ok().body(movieRepository.findAll());
    }

    public ResponseEntity<?> getMovie(Long id) {
        if (movieRepository.findById(id).isPresent()) {
            return ResponseEntity.ok().body(movieRepository.findById(id).get());
        }
        return ResponseEntity.badRequest().body("Error: Movie not found");
    }

    public ResponseEntity<?> getAllMoviesByActor(Long actorId) {
        if (actorRepository.findById(actorId).isPresent()) {
            Actor actor = actorRepository.findById(actorId).get();
            List<Movie> movieList = movieRepository.findAll();
            List<Movie> responseList = new ArrayList<>();
            for (Movie movie: movieList) {
                if (movie.getActors().contains(actor)) {
                    responseList.add(movie);
                }
            }
            return ResponseEntity.ok().body(responseList);
        }
        return ResponseEntity.badRequest().body("Error: could not find actor");
    }

    public ResponseEntity<?> getAllByDirector(Long directorId) {
        if (directorRepository.findById(directorId).isPresent()) {
            List<Movie> movieList = movieRepository.findAll();
            List<Movie> responseList = new ArrayList<>();
            for (Movie movie : movieList) {
                if (Objects.equals(movie.getDirector().getId(), directorId)) {
                    responseList.add(movie);
                }
            }
            return ResponseEntity.ok().body(responseList);
        }
        return ResponseEntity.badRequest().body("Error: Director not found");
    }
}
