package com.peeyush.E_commerce.controller;

import com.peeyush.E_commerce.dto.request.CreateUserRequest;
import com.peeyush.E_commerce.dto.request.UpdateUserRequest;
import com.peeyush.E_commerce.dto.response.UserResponse;
import com.peeyush.E_commerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse findUser(@PathVariable Long id){
        return userService.findUser(id);
    }

    @GetMapping
    public Page<UserResponse> findAll(Pageable pageable){
        return userService.findAll(pageable);
    }

    @PutMapping("/update/{id}")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request){
        return userService.updateById(request, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
