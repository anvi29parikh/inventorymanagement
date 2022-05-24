package com.shopify.inventorymanagement.service;

import com.shopify.inventorymanagement.dto.WarehouseDTO;

public interface WarehouseService {

	void insertUpdateWarehouse(WarehouseDTO warehouseDTO, Long id);
	
}
