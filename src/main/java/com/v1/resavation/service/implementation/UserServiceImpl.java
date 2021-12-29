package com.v1.resavation.service.implementation;

import com.v1.resavation.dto.UserDto;
import com.v1.resavation.exception.ApiBadRequestException;
import com.v1.resavation.model.Role;
import com.v1.resavation.model.User;
import com.v1.resavation.payload.response.RegistrationResponse;
import com.v1.resavation.repository.RoleRepository;
import com.v1.resavation.repository.UserRepository;
import com.v1.resavation.service.UserService;
import com.v1.resavation.utils.RoleAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private RoleAssignment roleAssignment;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           RoleAssignment roleAssignment, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleAssignment = roleAssignment;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public RegistrationResponse registerUser(UserDto userDto) {
        User user = null;
        try {
            if (userRepository.existsByEmail(userDto.getEmail())) {
                throw new ApiBadRequestException("Email already exists, Please try another email");

            }

            Set<String> stringList = userDto.getRoles();
            Set<Role> roleList = roleAssignment.assignRole(stringList, roleRepository);

            user = User.builder()
                    .email(userDto.getEmail())
                    .firstName(userDto.getFirstname())
                    .lastName(userDto.getLastname())
                    .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .roles(roleList).build();

            userRepository.save(user);
            RegistrationResponse.setMessage("Registered successfully");

        } catch (Exception e) {

            RegistrationResponse.setMessage("Registration failed successfully");
        }

        return RegistrationResponse.build(user);
    }


}
