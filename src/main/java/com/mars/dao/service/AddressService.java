package com.mars.dao.service;

import java.util.List;

import com.mars.dao.Address;

public interface AddressService {

	public Address getAddressFromAddressId(Integer addressId);
	
	public void createOrUpdateAddress(List<Address> addresses);
	
	public void deleteAddress(Integer addressId);
}
