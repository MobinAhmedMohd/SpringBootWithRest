package com.springboot.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "persons")
public class Person extends BaseDao{

	private String firstName;
	private String lastName;
	
	@OneToMany(mappedBy = "personId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Address> addresses;
	
	
}
