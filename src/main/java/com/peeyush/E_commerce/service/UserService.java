package com.peeyush.E_commerce.service;

import com.peeyush.E_commerce.dto.request.CreateUserRequest;
import com.peeyush.E_commerce.dto.request.UpdateUserRequest;
import com.peeyush.E_commerce.dto.response.UserResponse;
import com.peeyush.E_commerce.entity.Address;
import com.peeyush.E_commerce.entity.User;
import com.peeyush.E_commerce.mapper.UserMapper;
import com.peeyush.E_commerce.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse createUser(CreateUserRequest request){
        User user = userMapper.toEntity(request);
        Address address = userMapper.toAddress(request);
        user.setAddress(address);
        address.setUser(user);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public UserResponse findUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponse(user);
    }

    public Page<UserResponse> findAll(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toResponse);
    }

    public UserResponse updateById(UpdateUserRequest request, Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());
        return userMapper.toResponse(userRepository.save(user));
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
