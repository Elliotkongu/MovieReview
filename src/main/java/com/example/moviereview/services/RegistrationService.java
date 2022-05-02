package com.example.moviereview.services;

import com.example.moviereview.dtos.RegistrationDTO;
import com.example.moviereview.storage.actor.Actor;
import com.example.moviereview.storage.actor.ActorRepository;
import com.example.moviereview.storage.director.Director;
import com.example.moviereview.storage.director.DirectorRepository;
import com.example.moviereview.storage.reviewer.Reviewer;
import com.example.moviereview.storage.reviewer.ReviewerRepository;
import com.example.moviereview.storage.user.ERole;
import com.example.moviereview.storage.user.User;
import com.example.moviereview.storage.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final ReviewerRepository reviewerRepository;

    @Autowired
    public RegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                               ActorRepository actorRepository, DirectorRepository directorRepository,
                               ReviewerRepository reviewerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
        this.reviewerRepository = reviewerRepository;
    }

    public ResponseEntity<?> registerUser(RegistrationDTO registrationDTO) {
        if (userRepository.findByUsername(registrationDTO.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: username already registered");
        }

        User user = new User(registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()));

        if (registrationDTO.getRole().equalsIgnoreCase("ACTOR")) {
            return registerActor(user);
        } else if (registrationDTO.getRole().equalsIgnoreCase("DIRECTOR")) {
            return registerDirector(user);
        } else if (registrationDTO.getRole().equalsIgnoreCase("REVIEWER")) {
            return registerReviewer(user);
        } else {
            return ResponseEntity.badRequest().body("Error: Can't find role");
        }
    }

    private ResponseEntity<?> registerActor(User user) {
        Actor actor = new Actor();
        actor.setUserId(user.getId());
        actorRepository.save(actor);
        user.setRole(ERole.ROLE_ACTOR);
        userRepository.save(user);
        return ResponseEntity.ok().body("Successfully registered new actor");
    }

    private ResponseEntity<?> registerDirector(User user) {
        Director director = new Director();
        director.setUserId(user.getId());
        directorRepository.save(director);
        user.setRole(ERole.ROLE_DIRECTOR);
        userRepository.save(user);
        return ResponseEntity.ok().body("Successfully registered new director");
    }

    private ResponseEntity<?> registerReviewer(User user) {
        Reviewer reviewer = new Reviewer();
        reviewer.setUserId(user.getId());
        reviewerRepository.save(reviewer);
        user.setRole(ERole.ROLE_REVIEWER);
        userRepository.save(user);
        return ResponseEntity.ok().body("Successfully registered new reviewer");
    }
}
