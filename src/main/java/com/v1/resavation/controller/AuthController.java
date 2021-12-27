package com.v1.resavation.controller;

import com.v1.resavation.dto.UserDto;
import com.v1.resavation.payload.request.RegistrationRequest;
import com.v1.resavation.payload.response.RegistrationResponse;
import com.v1.resavation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(registrationRequest, UserDto.class);

        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);

    }

}
