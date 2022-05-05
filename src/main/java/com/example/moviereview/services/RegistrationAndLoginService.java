package com.example.moviereview.services;

import com.example.moviereview.dtos.RegistrationDTO;
import com.example.moviereview.storage.actor.Actor;
import com.example.moviereview.storage.actor.ActorRepository;
import com.example.moviereview.storage.director.Director;
import com.example.moviereview.storage.director.DirectorRepository;
import com.example.moviereview.storage.reviewer.Reviewer;
import com.example.moviereview.storage.reviewer.ReviewerRepository;
import com.example.moviereview.storage.user.Role.ERole;
import com.example.moviereview.storage.user.Role.Role;
import com.example.moviereview.storage.user.Role.RoleRepository;
import com.example.moviereview.storage.user.User;
import com.example.moviereview.storage.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationAndLoginService {
    private final UserRepository userRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final ReviewerRepository reviewerRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public RegistrationAndLoginService(UserRepository userRepository,
                                       ActorRepository actorRepository, DirectorRepository directorRepository,
                                       ReviewerRepository reviewerRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
        this.reviewerRepository = reviewerRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> registerUser(RegistrationDTO registrationDTO) {
        User user = new User(registrationDTO.getFirstName(),
                registrationDTO.getSurName(),
                registrationDTO.getDateOfBirth(),
                registrationDTO.getGender());
        userRepository.save(user);
        if (registrationDTO.getRole().equalsIgnoreCase("ACTOR")) {
            return registerActor(user);
        } else if (registrationDTO.getRole().equalsIgnoreCase("DIRECTOR")) {
            return registerDirector(user);
        } else if (registrationDTO.getRole().equalsIgnoreCase("REVIEWER")) {
            return registerReviewer(user);
        } else if (registrationDTO.getRole().equalsIgnoreCase("SYSADMIN")) {
            return registerSysAdmin(user);
        } else {
            return ResponseEntity.badRequest().body("Error: Can't find role");
        }
    }

    private ResponseEntity<?> registerActor(User user) {
        setRoleToSpecificUser(user, ERole.ROLE_ACTOR);
        userRepository.save(user);
        Actor actor = new Actor();
        actor.setUserId(user.getId());
        actorRepository.save(actor);
        return ResponseEntity.ok().body("Successfully registered new actor");
    }

    private ResponseEntity<?> registerDirector(User user) {
        setRoleToSpecificUser(user, ERole.ROLE_DIRECTOR);
        Director director = new Director();
        director.setUserId(user.getId());
        directorRepository.save(director);
        userRepository.save(user);
        return ResponseEntity.ok().body("Successfully registered new director");
    }

    private ResponseEntity<?> registerReviewer(User user) {
        setRoleToSpecificUser(user, ERole.ROLE_REVIEWER);
        Reviewer reviewer = new Reviewer();
        reviewer.setUserId(user.getId());
        reviewerRepository.save(reviewer);
        userRepository.save(user);
        return ResponseEntity.ok().body("Successfully registered new reviewer");
    }

    private ResponseEntity<?> registerSysAdmin(User user) {
        setRoleToSpecificUser(user, ERole.ROLE_SYSADMIN);
        userRepository.save(user);
        return ResponseEntity.ok().body("Successfully registered a new admin");
    }

    private void setRoleToSpecificUser(User user, ERole role) {
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
        roles.add(userRole);
        user.setRole(roles);
    }
}
