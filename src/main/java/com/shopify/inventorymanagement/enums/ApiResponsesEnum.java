package com.shopify.inventorymanagement.enums;

/**
 * <h1>ApiResponsesEnum</h1>
 * <p>
 * This enum will be used to return success api response messages
 * </p>
 *
 */
public enum ApiResponsesEnum {
	ID_NOT_PRESENT("Id not Present"),
	PRODUCT_ADDED_SUCCESSFULLY("Product added to inventory successfully"),
	WAREHOUSE_ADDED_SUCCESSFULLY("Warehouse added to inventory successfully"),
	WAREHOUSE_UPDATED_SUCCESSFULLY("Warehouse added to inventory successfully"),
	PRODUCT_UPDATED_SUCCESSFULLY("Product updated to inventory successfully"),
	PRODUCT_DELETED_SUCCESSFULLY("Product deleted to inventory successfully"),
	;

	private final String value;

	private String message;

	ApiResponsesEnum(String value) {
		this.value = value;
	}

	ApiResponsesEnum(String message, String value) {
		this.message = message;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}
}
