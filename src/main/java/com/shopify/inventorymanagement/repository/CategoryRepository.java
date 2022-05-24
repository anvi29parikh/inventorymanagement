package com.shopify.inventorymanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopify.inventorymanagement.models.Category;

@Repository(value="CategoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long>  {

	Optional<Category> findByIdAndStatus(Long categoryId, boolean status);
	
}