package com.shopify.inventorymanagement.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.shopify.inventorymanagement.dto.ProductDTO;
import com.shopify.inventorymanagement.enums.ExceptionEnum;
import com.shopify.inventorymanagement.exception.CustomException;
import com.shopify.inventorymanagement.models.Category;
import com.shopify.inventorymanagement.models.Inventory;
import com.shopify.inventorymanagement.models.Products;
import com.shopify.inventorymanagement.models.Users;
import com.shopify.inventorymanagement.models.Warehouse;
import com.shopify.inventorymanagement.repository.CategoryRepository;
import com.shopify.inventorymanagement.repository.InventoryRepository;
import com.shopify.inventorymanagement.repository.ProductRepository;
import com.shopify.inventorymanagement.repository.WarehouseRepository;
import com.shopify.inventorymanagement.service.ProductService;
import com.shopify.inventorymanagement.utils.BaseMethods;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public void insertUpdateProduct(ProductDTO productDTO, Long productId) {
		
		Optional<Warehouse> warehouse = warehouseRepository.findByIdAndStatus(productDTO.getWarehourseId(), true);
		
		if(!warehouse.isPresent()) {
			throw new CustomException("Warehouse Id is not present in Database", HttpStatus.NOT_FOUND);
		}
		
		Optional<Category> category = categoryRepository.findByIdAndStatus(productDTO.getCategoryId(), true);
		
		if(!category.isPresent()) {
			throw new CustomException("Category Id is not present in Database", HttpStatus.NOT_FOUND);
		}
		
		Products product = convertProductDTOToProduct(productDTO, productId);
		productRepository.save(product);
		
		Optional<Inventory> inventory = null;
		
		if(productId != null) {
			inventory = inventoryRepository.findByWarehouseIdAndProductId(productDTO.getWarehourseId(), productId);
		}else {
			inventory = inventoryRepository.findByWarehouseIdAndProductName(productDTO.getWarehourseId(), productDTO.getName());
		}
		
		if(inventory.isPresent()) {
			//Update
			Inventory updateInventory = inventory.get();
			updateInventory.setUnitInStock((updateInventory.getUnitInStock()+1));
			updateInventory.setUpdatedDate(BaseMethods.generateDate());
			inventoryRepository.save(updateInventory);
		}else {
			//Insert
			Inventory insertInventory = new Inventory();
			insertInventory.setWarehouse(warehouse.get());
			insertInventory.setProducts(product);
			insertInventory.setCreatedBy(BaseMethods.generateStaticUser(1l));
			insertInventory.setUpdatedBy(BaseMethods.generateStaticUser(1l));
			insertInventory.setCreatedDate(BaseMethods.generateDate());
			insertInventory.setUpdatedDate(BaseMethods.generateDate());	
			inventoryRepository.save(insertInventory);
		}	
		
	}

	public Products convertProductDTOToProduct(ProductDTO productDTO, Long productId) {
		Users user = BaseMethods.generateStaticUser(null);
		Products product = new Products(productDTO.getName(), productDTO.getPrice(), productDTO.getTaxing(),
				productDTO.getManufactureDate(), productDTO.getExpireDate());
		product.setCreatedDate(BaseMethods.generateDate());
		product.setUpdatedDate(BaseMethods.generateDate());
		product.setStatus(true);
		product.setDeactivate(false);
		product.setCreatedBy(user);
		product.setUpdatedBy(user);
		product.setId(productId);
		return product;
	}

	@Override
	public void deleteProduct(Long productId) {
		
		Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
		if(inventory.isPresent()) {
			//Update
			Inventory updateInventory = inventory.get();
			updateInventory.setUnitOutStock((updateInventory.getUnitOutStock()+1));
			updateInventory.setUnitInStock((updateInventory.getUnitInStock()-1));
			updateInventory.setUpdatedDate(BaseMethods.generateDate());
			inventoryRepository.save(updateInventory);
		}else {
			throw new CustomException(ExceptionEnum.PRODUCT_NOT_IN_INVENTORY.getValue(), HttpStatus.NOT_FOUND);
		}
		
		Optional<Products> productOptional= productRepository.findByIdAndStatus(productId,true);
		if(productOptional.isPresent()) {
			//Product is available in database
			Products updateProduct = productOptional.get();
			updateProduct.setStatus(false);
			updateProduct.setDeactivate(true);
			productRepository.save(updateProduct);	
		}else {
			//Product is not available in database
			throw new CustomException(ExceptionEnum.PRODUCT_ID_INVALID.getValue(), HttpStatus.NOT_FOUND);
		}
		
	}

}
