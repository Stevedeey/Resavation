package com.v1.resavation.dto;

import com.v1.resavation.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String lastname;

    private String firstname;

    private String email;

    private String password;

    private String verifyPassword;

    private String encryptedPassword;

    private boolean terms;

    private Set<String> roles;


}
