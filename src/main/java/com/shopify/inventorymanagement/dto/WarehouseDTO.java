package com.shopify.inventorymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {
	
	private String name;

	private Double uniqueId;

	private String addressLine1;

	private String addressLine2;

	private String city;

	private String state;
	
	private String country;
	
	private String zipCode;

}
