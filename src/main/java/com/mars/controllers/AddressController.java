package com.mars.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mars.dao.Address;
import com.mars.dao.service.AddressService;

@RestController
@Service
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@RequestMapping(value = "/editAddress/{addressId}",  method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String editAddress(@RequestBody Address address, @PathVariable Integer addressId){
		String retStatement = "";
		try {
			if(addressId != null && address.getId() !=null && addressId.equals(address.getId())) {
				Address addressTemp = addressService.getAddressFromAddressId(addressId);
				if(addressTemp == null) {
					retStatement = "ADDRESS ID NOT FOUND";
				}else {
					address.setPersonId(addressTemp.getPersonId());
					List<Address> addresses = new ArrayList<>();
					addresses.add(address);
					addressService.createOrUpdateAddress(addresses);
					retStatement = "SUCESSFULLY EDITED";
				}
			}else {
				retStatement = "NOT EDITED. MAY BE BODY ADDRESS_ID AND PARAMETER ADDRESS_ID NOT MATCHING";
			}
		}catch(Exception e) {
			e.printStackTrace();
			retStatement = "ERROR WHILE EDITING";
		}
		
		
		return retStatement;
	}
	
	@RequestMapping(value = "/deleteAddress/{addressId}",  method = RequestMethod.DELETE)
	public String deleteAddress(@PathVariable Integer addressId){
		String retStatement = "";
		try {
			if(addressId != null ) {
				Address addressTemp = addressService.getAddressFromAddressId(addressId);
				if(addressTemp == null) {
					retStatement = "ADDRESS ID NOT FOUND";
				}else {
					addressService.deleteAddress(addressId);
					retStatement = "ADDRESS ID DELETED";
				}
			}else {
				retStatement = "ADDRESS CANNOT BE NULL";
			}
		}catch(Exception e) {
			e.printStackTrace();
			retStatement = "ERROR WHILE DELETING";
		}
		
		
		return retStatement;
	}
}
