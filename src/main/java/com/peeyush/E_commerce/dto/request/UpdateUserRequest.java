package com.peeyush.E_commerce.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
        @NotBlank
        String username,

        @NotBlank
        String email,

        @NotBlank
        String password
){}
