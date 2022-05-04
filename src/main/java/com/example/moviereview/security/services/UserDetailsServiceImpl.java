package com.example.moviereview.security.services;

import com.example.moviereview.storage.user.User;
import com.example.moviereview.storage.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            if (userRepository.findByUsername(username).isPresent()) {
                user = userRepository.findByUsername(username).get();
                return UserDetailsImpl.build(user);
            }
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }

    }

}
