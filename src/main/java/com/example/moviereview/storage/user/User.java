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
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = new HashSet<>();
    }
}
