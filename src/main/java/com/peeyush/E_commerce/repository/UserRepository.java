package com.peeyush.E_commerce.repository;

import com.peeyush.E_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
