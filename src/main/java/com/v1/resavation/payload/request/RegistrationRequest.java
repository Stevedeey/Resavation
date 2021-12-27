package com.v1.resavation.payload.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RegistrationRequest {

    @NotNull(message = "{name.null}")
    @NotBlank(message = "{name.empty}")
    private String lastname;

    @NotNull(message = "{name.null}")
    @NotBlank(message = "{name.empty}")
    private String firstname;

    @NotNull(message = "{emailNull}")
    @Email(message = "{email.invalid}")
    @Size(max = 50, message = "email should contain characters not more than 50")
    @NotBlank(message = "{email.empty}")
    private String email;

    @NotNull(message = "{password.null}")
    @NotBlank(message = "{password.empty}")
    @Size(min = 8, message = "password should be 8 characters or more")
    private String password;

    @NotNull(message = "{password.null}")
    @NotBlank(message = "{password.empty}")
    private String verifyPassword;

    @NotNull(message = "{termsNull}")
    private boolean termAndCondition;

}
