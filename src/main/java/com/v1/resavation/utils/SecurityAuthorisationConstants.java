package com.v1.resavation.utils;

public class SecurityAuthorisationConstants {
    public static final String[] PUBLIC_URIS = new String[]{
            "/",
            // -- Swagger UI v3 (OpenAPI) Start
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui/login/",
            "/swagger-ui/api/login/",
            "/swagger-ui/#/**",
            // -- Swagger UI v3 (OpenAPI) End
            "/login",
            "/api/login",
            "api/login/",
            "/#/api/login/",
            "/register",
            "/api/register",
            "/api/admin/**",
            "/api/**",
            "/api/admin/products/**",
            "/api/admin/password-forgot",
            "/admin-controller/**",
            "/api/admin/password-reset",
            "/api/customer/password-forgot",
            "/api/customer/password-reset/",
            // -- Swagger UI v3 (OpenAPI) End
            "/products/**",
    };
}
