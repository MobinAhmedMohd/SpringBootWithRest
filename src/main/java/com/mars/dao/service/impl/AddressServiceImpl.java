package com.mars.dao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.dao.Address;
import com.mars.dao.repositories.AddressRepository;
import com.mars.dao.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	AddressRepository addressRepository; 
	
	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	

	@Override
	public Address getAddressFromAddressId(Integer addressId) {
		return addressRepository.getAddressesOnAddressId(addressId);
	}

	@Override
	public void createOrUpdateAddress(List<Address> addresses) {
		
		addresses.forEach(address -> {
			addressRepository.save(address);
		});

	}


	@Override
	public void deleteAddress(Integer addressId) {
		addressRepository.delete(addressId);
		
	}

}
