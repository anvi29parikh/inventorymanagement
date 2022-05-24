package com.shopify.inventorymanagement.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	private String middleName;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	private String phone;

	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;

	private String addressLine1;
	
	private String addressLine2;
	
	private String city;

	private String state;
	
	private String country;
	
	private String zipCode;

	private boolean emailVerified;

	private boolean phoneVerified;
	
	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", referencedColumnName = "id")
	private Users createdBy;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "updated_by", referencedColumnName = "id")
	private Users updatedBy;

	public Users(Long id) {
		this.id = id;
	}

}
