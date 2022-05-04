package com.example.moviereview.services;

import com.example.moviereview.storage.user.Role.ERole;
import com.example.moviereview.storage.user.Role.Role;
import com.example.moviereview.storage.user.Role.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadDataService implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(LoadDataService.class);

    private final RoleRepository roleRepository;

    @Autowired
    public LoadDataService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        if (roleRepository.findByName(ERole.ROLE_ACTOR).isEmpty()) {
            logger.info("{}", roleRepository.save(new Role(ERole.ROLE_ACTOR)).getName());
        }
        if (roleRepository.findByName(ERole.ROLE_DIRECTOR).isEmpty()) {
            logger.info("{}", roleRepository.save(new Role(ERole.ROLE_DIRECTOR)).getName());
        }
        if (roleRepository.findByName(ERole.ROLE_REVIEWER).isEmpty()) {
            logger.info("{}", roleRepository.save(new Role(ERole.ROLE_REVIEWER)).getName());
        }
        if (roleRepository.findByName(ERole.ROLE_SYSADMIN).isEmpty()) {
            logger.info("{}", roleRepository.save(new Role(ERole.ROLE_SYSADMIN)).getName());
        }
    }
}
