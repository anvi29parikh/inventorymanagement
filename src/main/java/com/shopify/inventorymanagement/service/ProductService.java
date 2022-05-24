package com.shopify.inventorymanagement.service;

import com.shopify.inventorymanagement.dto.ProductDTO;

public interface ProductService {

	void insertUpdateProduct(ProductDTO productDTO, Long productId);

	void deleteProduct(Long productId);
	
}
