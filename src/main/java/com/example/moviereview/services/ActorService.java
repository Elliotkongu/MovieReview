package com.example.moviereview.services;

import com.example.moviereview.dtos.ActorDTO;
import com.example.moviereview.storage.actor.Actor;
import com.example.moviereview.storage.actor.ActorRepository;
import com.example.moviereview.storage.user.User;
import com.example.moviereview.storage.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;
    private final UserRepository userRepository;
    @Autowired
    public ActorService(ActorRepository actorRepository, UserRepository userRepository) {
        this.actorRepository = actorRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getAll() {
        List<Actor> actorList = actorRepository.findAll();
        List<ActorDTO> responseList = new ArrayList<>();
        for (Actor actor : actorList) {
            if (userRepository.findById(actor.getUserId()).isPresent()) {
                User user = userRepository.findById(actor.getUserId()).get();
                ActorDTO dto = new ActorDTO(actor.getId(), user.getId(), user.getFirstName(),
                        user.getSurName(), user.getDateOfBirth(), user.getGender());
                responseList.add(dto);
            }
        }
        return ResponseEntity.ok().body(responseList);
    }

    public ResponseEntity<?> getActor(Long id) {
        if (actorRepository.findById(id).isPresent()) {
            Actor actor = actorRepository.findById(id).get();
            if (userRepository.findById(actor.getUserId()).isPresent()) {
                User user = userRepository.findById(actor.getUserId()).get();
                return ResponseEntity.ok().body(new ActorDTO(actor.getId(), user.getId(), user.getFirstName(),
                        user.getSurName(), user.getDateOfBirth(), user.getGender()));
            }
            return ResponseEntity.badRequest().body("Error: User not found");
        }
        return ResponseEntity.badRequest().body("Error: Actor not found");
    }
}
