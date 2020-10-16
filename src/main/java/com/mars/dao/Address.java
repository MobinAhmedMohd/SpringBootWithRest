package com.mars.dao;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "Addresses")
public class Address extends BaseDao{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "personId", referencedColumnName = "id")
	@JsonBackReference
	private Person personId;
	
	private String addressType;
	private String street;
	private String city;
	private String postalCode;

}
