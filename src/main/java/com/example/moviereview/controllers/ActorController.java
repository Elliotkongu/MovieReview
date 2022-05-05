package com.example.moviereview.controllers;

import com.example.moviereview.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/i/actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllActors() {
        return actorService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getActor(@PathVariable("id") Long id) {
        return actorService.getActor(id);
    }
}
