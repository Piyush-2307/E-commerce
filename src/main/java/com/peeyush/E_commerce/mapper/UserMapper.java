package com.peeyush.E_commerce.mapper;

import com.peeyush.E_commerce.dto.request.CreateUserRequest;
import com.peeyush.E_commerce.dto.response.AddressResponse;
import com.peeyush.E_commerce.dto.response.UserResponse;
import com.peeyush.E_commerce.entity.Address;
import com.peeyush.E_commerce.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper){
        this. addressMapper = addressMapper;
    }

    public User toEntity(CreateUserRequest request){
        return new User(
                request.username(),
                request.email(),
                request.password()
        );
    }

    public UserResponse toResponse(User user){

        Address address = user.getAddress();

        AddressResponse addressResponse = null;

         if (address != null){
             addressResponse = addressMapper.toAddressResponse(address);
         }

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                addressResponse
        );
    }

    public Address toAddress(CreateUserRequest request){
        return new Address(request.street(), request.city(), request.state(), request.pinCode());
    }
}
