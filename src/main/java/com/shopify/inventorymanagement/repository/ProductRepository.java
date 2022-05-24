package com.shopify.inventorymanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopify.inventorymanagement.models.Products;

@Repository(value="ProductRepository")
public interface ProductRepository extends JpaRepository<Products, Long>  {

	Optional<Products> findByIdAndStatus(Long productId, boolean status);
	
}
