package com.v1.resavation.service;

import com.v1.resavation.dto.UserDto;
import com.v1.resavation.payload.response.RegistrationResponse;

public interface UserService {

        RegistrationResponse registerUser(UserDto userDto);
}
