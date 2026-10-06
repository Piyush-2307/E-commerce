package com.peeyush.E_commerce.repository;

import com.peeyush.E_commerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}