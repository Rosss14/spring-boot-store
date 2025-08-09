package com.codewithmosh.store.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequest {
    @NotBlank(message="Email cannot be blank")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
