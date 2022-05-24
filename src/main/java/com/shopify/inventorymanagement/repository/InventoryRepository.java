package com.shopify.inventorymanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopify.inventorymanagement.models.Inventory;

@Repository(value="InventoryRepository")
public interface InventoryRepository extends JpaRepository<Inventory, Long>  {

	Optional<Inventory> findByIdAndStatus(Long inventoryId, boolean status);

	@Query(value = "from Inventory i where i.warehouse.id = ?1 and i.warehouse.status=true and i.products.name=?2")
	Optional<Inventory> findByWarehouseIdAndProductName(Long warehourseId, String productName);	
	
	@Query(value = "from Inventory i where i.warehouse.id = ?1 and i.warehouse.status=true and i.products.id=?2")
	Optional<Inventory> findByWarehouseIdAndProductId(Long warehourseId, Long productId);	
	
	@Query(value = "from Inventory i where i.products.id=?2 and i.unitInStock > 0")
	Optional<Inventory> findByProductId( Long productId);	
	
}