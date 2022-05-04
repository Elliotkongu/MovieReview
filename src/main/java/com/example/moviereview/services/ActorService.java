package com.example.moviereview.services;

import com.example.moviereview.storage.actor.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(actorRepository.findAll());
    }
}
