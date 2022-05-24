package com.shopify.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopify.inventorymanagement.models.Users;

@Repository(value="UsersRepository")
public interface UsersRepository extends JpaRepository<Users, Long>{
}
