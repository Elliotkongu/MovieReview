package com.example.moviereview.controllers;

import com.example.moviereview.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/i/directors")
public class DirectorController {

    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllActors() {
        return directorService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getDirector(@PathVariable("id") Long id) {
        return directorService.getDirector(id);
    }
}
