package com.peeyush.E_commerce.mapper;

import com.peeyush.E_commerce.dto.request.CreateUserRequest;
import com.peeyush.E_commerce.dto.response.UserResponse;
import com.peeyush.E_commerce.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(CreateUserRequest request){
        return new User(
                request.username(),
                request.email(),
                request.password()
        );
    }

    public UserResponse toResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
