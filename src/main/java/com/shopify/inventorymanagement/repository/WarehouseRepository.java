package com.shopify.inventorymanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopify.inventorymanagement.models.Warehouse;

@Repository(value="WarehouseRepository")
public interface WarehouseRepository extends JpaRepository<Warehouse, Long>  {

	Optional<Warehouse> findByIdAndStatus(Long warehouseId, boolean status);
	
}