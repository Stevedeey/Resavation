package com.v1.resavation.service;

import com.v1.resavation.dto.UserDto;
import com.v1.resavation.payload.response.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

        RegistrationResponse registerUser(UserDto userDto);
}
