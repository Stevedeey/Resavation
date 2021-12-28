package com.v1.resavation.utils;



import com.v1.resavation.exception.ApiResourceNotFoundException;
import com.v1.resavation.model.ERole;
import com.v1.resavation.model.Role;
import com.v1.resavation.repository.RoleRepository;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Set;

@Component
public class RoleAssignment {

    public Set<Role> assignRole(Set<String> userList, RoleRepository roleRepository) {

        Set<Role> roles = new HashSet<>();

        if (userList == null) {
            Role user = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new ApiResourceNotFoundException("No Such Role"));
            roles.add(user);
        } else {
            userList.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role admin = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new ApiResourceNotFoundException("Error: Role not found"));
                        roles.add(admin);

                        break;

                    default:
                        Role user = roleRepository.findByName(ERole.USER)
                                .orElseThrow(() -> new ApiResourceNotFoundException("Error: Role not found"));
                        roles.add(user);

                        break;
                }
            });
        }
        return roles;
    }
}
