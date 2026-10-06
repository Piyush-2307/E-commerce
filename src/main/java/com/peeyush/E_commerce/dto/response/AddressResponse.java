package com.peeyush.E_commerce.dto.response;

import com.peeyush.E_commerce.entity.User;

public record AddressResponse(
        Long id,
        String street,
        String city,
        String state,
        String pinCode,
        Long userId
) {
}
