package com.shopify.inventorymanagement.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.shopify.inventorymanagement.dto.WarehouseDTO;
import com.shopify.inventorymanagement.exception.CustomException;
import com.shopify.inventorymanagement.models.Warehouse;
import com.shopify.inventorymanagement.repository.WarehouseRepository;
import com.shopify.inventorymanagement.service.WarehouseService;
import com.shopify.inventorymanagement.utils.BaseMethods;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@Override
	public void insertUpdateWarehouse(WarehouseDTO warehouseDTO, Long id) {
		
		Warehouse warehouse = new Warehouse(id, warehouseDTO.getName(), BaseMethods.generateBatchNumber(),
				warehouseDTO.getAddressLine1(), warehouseDTO.getAddressLine2(), warehouseDTO.getCity(),
				warehouseDTO.getState(), warehouseDTO.getCountry(), warehouseDTO.getZipCode());
		warehouse.setUpdatedDate(BaseMethods.generateDate());
		if(id != null) {
			Optional<Warehouse> updateWarehouse =  this.warehouseRepository.findById(id);
			if(!updateWarehouse.isPresent()) {
				throw new CustomException("Warehouse not found in database", HttpStatus.NOT_FOUND);
			}
			warehouse.setUniqueId(updateWarehouse.get().getUniqueId());
			warehouse.setCreatedBy(updateWarehouse.get().getCreatedBy());
			warehouse.setCreatedDate(updateWarehouse.get().getCreatedDate());
		}else {
			warehouse.setCreatedBy(BaseMethods.generateStaticUser(2l));
			warehouse.setCreatedDate(BaseMethods.generateDate());
			warehouse.setUpdatedBy(BaseMethods.generateStaticUser(2l));
		}
		this.warehouseRepository.save(warehouse);
	}

}
