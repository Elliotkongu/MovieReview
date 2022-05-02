package com.example.moviereview.security.userdetails;


import com.example.moviereview.storage.user.User;
import com.example.moviereview.storage.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        try {
            if (userRepository.findByUsername(username).isPresent()){
                user = userRepository.findByUsername(username).get();
            }
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User with that username not found");
        }
        return UserDetailsImpl.build(user);
    }
}
