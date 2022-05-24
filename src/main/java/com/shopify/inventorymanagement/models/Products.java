package com.shopify.inventorymanagement.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "products")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products extends BaseEntity implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Double price;
	
	private Double taxing;
	
	private String batchNum;
	
	private Date manufactureDate;
	
	private Date importDate;
	
	private Date expireDate;
	
	private String comment;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "cateogry", referencedColumnName = "id")
	private Category category;
	
	public Products(String name, Double price, Double taxing, Date manufactureDate, Date expireDate) {
		this.name = name;
		this.price = price;
		this.taxing = taxing;
		this.manufactureDate = manufactureDate;
		this.expireDate = expireDate;
	}
	
}
