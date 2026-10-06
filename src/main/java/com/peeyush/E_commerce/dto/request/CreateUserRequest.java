package com.peeyush.E_commerce.dto.request;

public record CreateUserRequest(
        String username,
        String email,
        String password
) {
}
