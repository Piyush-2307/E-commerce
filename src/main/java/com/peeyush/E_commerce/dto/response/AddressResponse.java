package com.peeyush.E_commerce.dto.response;

public record AddressResponse(
        Long id,
        String street,
        String city,
        String state,
        String pinCode,
        Long userId
) {
}
