package com.example.moviereview.storage.user;

import com.example.moviereview.storage.user.Role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> role;
    private String firstName;
    private String surName;
    private String dateOfBirth;
    private String gender;

    public User(String firstName, String surName, String dateOfBirth, String gender) {
        this.firstName = firstName;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.role = new HashSet<>();
    }
}
