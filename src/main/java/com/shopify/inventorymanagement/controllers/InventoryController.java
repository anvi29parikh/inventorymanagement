package com.shopify.inventorymanagement.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopify.inventorymanagement.dto.ApiResponse;
import com.shopify.inventorymanagement.dto.ProductDTO;
import com.shopify.inventorymanagement.enums.ApiResponsesEnum;
import com.shopify.inventorymanagement.enums.ExceptionEnum;
import com.shopify.inventorymanagement.exception.CustomException;
import com.shopify.inventorymanagement.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("inventory-management/inventory")
public class InventoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	ProductService productService;
	
	@Operation(summary = "This is to insert the product to the inventory")
	@PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> insertProduct(@Valid @RequestBody ProductDTO productDTO,
			HttpServletRequest request) {
		try {
			productService.insertUpdateProduct(productDTO, null);
			return new ResponseEntity<>(
					new ApiResponse(HttpStatus.OK, ApiResponsesEnum.PRODUCT_ADDED_SUCCESSFULLY.getValue(), productDTO),
					HttpStatus.OK);
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("Exception {1}", e);
			throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@Operation(summary = "This is to update the product to the inventory")
	@PutMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> updateProduct(@Valid @RequestBody ProductDTO productDTO,
			HttpServletRequest request,@PathVariable(value = "productId") Long productId) {
		try {
			productService.insertUpdateProduct(productDTO, productId);
			return new ResponseEntity<>(
					new ApiResponse(HttpStatus.OK, ApiResponsesEnum.PRODUCT_UPDATED_SUCCESSFULLY.getValue(), productDTO),
					HttpStatus.OK);
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("Exception {1}", e);
			throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "This is to delete the product to the inventory")
	@DeleteMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable(value = "productId") Long productId,HttpServletRequest request) {
		try {
			productService.deleteProduct(productId);
			return new ResponseEntity<>(
					new ApiResponse(HttpStatus.OK, ApiResponsesEnum.PRODUCT_DELETED_SUCCESSFULLY.getValue()),
					HttpStatus.OK);
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("Exception {1}", e);
			throw new CustomException(ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
