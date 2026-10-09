package com.peeyush.E_commerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank
        String username,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password,

        @NotBlank
        String street,

        @NotBlank
        String city,

        @NotBlank
        String state,

        @NotBlank
        String pinCode
) {
}
