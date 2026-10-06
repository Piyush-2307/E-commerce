package com.peeyush.E_commerce.dto.request;

public record CreateAddressRequest(
        String street,
        String city,
        String state,
        String pinCode
) {
}
