package com.springboot.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.dao.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	@Query(value = "SELECT address FROM Address AS address WHERE address.id = :addressId")
	Address getAddressesOnAddressId(@Param("addressId") Integer addressId);
}
