package com.peeyush.E_commerce.mapper;

import com.peeyush.E_commerce.dto.request.CreateUserRequest;
import com.peeyush.E_commerce.dto.response.AddressResponse;
import com.peeyush.E_commerce.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address toAddressEntity(CreateUserRequest request){
        return new Address(
                request.street(),
                request.city(),
                request.state(),
                request.pinCode()
        );
    }

    public AddressResponse toAddressResponse(Address address){
        return new AddressResponse(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getPinCode(),
                address.getUser().getId()
        );
    }
}
