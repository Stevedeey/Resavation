package com.v1.resavation.utils;


import com.v1.resavation.model.ERole;
import com.v1.resavation.model.Role;
import com.v1.resavation.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DatabaseSeeding {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static RoleRepository roleRepository = null;

    public DatabaseSeeding(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void seed() {

        try {
            seedAdminRole();
            seedUserRole();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private void seedUserRole() {
        Role user = Role.builder()
                .name(ERole.USER)
                .build();

        Optional<Role> role = roleRepository.findByName(ERole.USER);
        if(role.isEmpty()) {
            roleRepository.save(user);
        }

    }

    private void seedAdminRole() {
        Role admin = Role.builder()
                .name(ERole.ADMIN)
                .build();

        Optional<Role> role = roleRepository.findByName(ERole.ADMIN);
        if(role.isEmpty()) {
            roleRepository.save(admin);
        }
    }

}
