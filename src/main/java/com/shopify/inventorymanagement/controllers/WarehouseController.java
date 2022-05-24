package com.shopify.inventorymanagement.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.inventorymanagement.dto.ApiResponse;
import com.shopify.inventorymanagement.dto.WarehouseDTO;
import com.shopify.inventorymanagement.enums.ApiResponsesEnum;
import com.shopify.inventorymanagement.enums.ExceptionEnum;
import com.shopify.inventorymanagement.exception.CustomException;
import com.shopify.inventorymanagement.service.WarehouseService;

@RestController
@RequestMapping("/inventory-management/warehouse")
public class WarehouseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	WarehouseService warehouseService;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> createWarehouse(@Valid @RequestBody WarehouseDTO WarehouseDTO,
			HttpServletRequest request) {
		try {
			warehouseService.insertUpdateWarehouse(WarehouseDTO, null);
			return new ResponseEntity<>(
					new ApiResponse(HttpStatus.OK, ApiResponsesEnum.WAREHOUSE_ADDED_SUCCESSFULLY.getValue(), WarehouseDTO),
					HttpStatus.OK);
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("Exception {1}", e);
			throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping(value = "/{warehouseId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> updateWarehouse(@Valid @RequestBody WarehouseDTO WarehouseDTO,
			HttpServletRequest request,@PathVariable(value = "warehouseId") Long warehouseId) {
		try {
			warehouseService.insertUpdateWarehouse(WarehouseDTO, warehouseId);
			return new ResponseEntity<>(
					new ApiResponse(HttpStatus.OK, ApiResponsesEnum.WAREHOUSE_UPDATED_SUCCESSFULLY.getValue(), WarehouseDTO),
					HttpStatus.OK);
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("Exception {1}", e);
			throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
