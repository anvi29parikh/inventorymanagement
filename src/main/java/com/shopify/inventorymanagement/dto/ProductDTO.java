package com.shopify.inventorymanagement.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

	@NotNull(message = "Product name must not be null")
	private String name;
	@NotNull
	@Size(min = 2, max = 14, message = "Product price must be grather than zero")
	private Double price;
	private Double taxing;
	@NotNull
	private Long categoryId;
	@NotNull
	private Long warehourseId;
	@NotNull
	private Date manufactureDate;
	private Date expireDate;
	private String comment;

}
