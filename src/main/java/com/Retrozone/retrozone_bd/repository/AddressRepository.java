package com.Retrozone.retrozone_bd.repository;

import com.Retrozone.retrozone_bd.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(Long userId);
}