package com.v1.resavation.payload.response;

import com.v1.resavation.model.User;


public class RegistrationResponse {

    private  static  String message;

    private User user;

    public RegistrationResponse(String n, User user) {
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        RegistrationResponse.message = message;
    }

    public static RegistrationResponse build(User user) {

        return new RegistrationResponse(getMessage(),
                new User(user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRoles()));
    }
}
