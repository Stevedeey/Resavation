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
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ApiBadRequestException("Email already exists, Please try another email");

        }

        if (!isEmailValid(userDto.getEmail())) {
            throw new ApiBadRequestException("Invalid Email Format, Try again!!");

        }

        if (!isValidPassword(userDto.getPassword())) {
            throw new ApiBadRequestException("Invalid password, Try again!!");
        }

        if (!userDto.getPassword().equals(userDto.getVerifyPassword())) {

            throw new ApiBadRequestException("Password mismatched, Try again!!");
        }

        if(!userDto.isTerms()){
            throw new ApiBadRequestException("Terms and condition box must be checked!!");
        }

        Set<String> stringList = userDto.getRoles();
        Set<Role> roleList = roleAssignment.assignRole(stringList, roleRepository);

        User user = User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstname())
                .lastName(userDto.getLastname())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .roles(roleList).build();

        try {

            userRepository.save(user);
            RegistrationResponse.setMessage("Registered successfully");

        } catch (Exception e) {

            RegistrationResponse.setMessage("Registration failed successfully");
        }

        return RegistrationResponse.build(user);
    }


    private boolean isEmailValid(String password) {
        String regex = "^(.+)@(\\w+)\\.(\\w+)$";

        Pattern pattern = Pattern.compile(regex);
        if (password == null) {
            throw new ApiBadRequestException("Error: email cannot ne null");
        }

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        String regex = "^(([0-9]|[a-z]|[A-Z]|[@])*){8,20}$";

        Pattern pattern = Pattern.compile(regex);
        if (password == null) {
            throw new ApiBadRequestException("Error: password cannot ne null");
        }

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
