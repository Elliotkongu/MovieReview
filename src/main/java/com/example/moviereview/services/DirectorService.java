package com.example.moviereview.services;

import com.example.moviereview.dtos.ActorDTO;
import com.example.moviereview.dtos.DirectorDTO;
import com.example.moviereview.storage.actor.Actor;
import com.example.moviereview.storage.director.Director;
import com.example.moviereview.storage.director.DirectorRepository;
import com.example.moviereview.storage.user.User;
import com.example.moviereview.storage.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final UserRepository userRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository, UserRepository userRepository) {
        this.directorRepository = directorRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<?> getAll() {
        List<Director> directorList = directorRepository.findAll();
        List<DirectorDTO> responseList = new ArrayList<>();
        for (Director director : directorList) {
            if (userRepository.findById(director.getUserId()).isPresent()) {
                User user = userRepository.findById(director.getUserId()).get();
                DirectorDTO dto = new DirectorDTO(director.getId(), user.getId(), user.getFirstName(), user.getSurName(), user.getDateOfBirth(), user.getGender());
                responseList.add(dto);
            }
        }
        return ResponseEntity.ok().body(responseList);
    }

    public ResponseEntity<?> getDirector(Long id) {
        if (directorRepository.findById(id).isPresent()) {
            Director director = directorRepository.findById(id).get();
            if (userRepository.findById(director.getUserId()).isPresent()) {
                User user = userRepository.findById(director.getUserId()).get();
                return ResponseEntity.ok().body(new ActorDTO(director.getId(), user.getId(), user.getFirstName(),
                        user.getSurName(), user.getDateOfBirth(), user.getGender()));
            }
            return ResponseEntity.badRequest().body("Error: User not found");
        }
        return ResponseEntity.badRequest().body("Error: Director not found");
    }
}
