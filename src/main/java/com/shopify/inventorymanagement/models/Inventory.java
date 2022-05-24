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

@Table(name = "inventory")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer unitInStock;
	
	private Integer unitOutStock;
	
	private String description;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "products", referencedColumnName = "id")
	private Products products;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "warehouse", referencedColumnName = "id")
	private Warehouse warehouse;

}
