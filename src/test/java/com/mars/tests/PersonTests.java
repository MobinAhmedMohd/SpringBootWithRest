package com.mars.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mars.controllers.AddressController;
import com.mars.controllers.PersonController;
import com.mars.dao.Address;
import com.mars.dao.Person;
import com.mars.main.ApplicationMain;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationMain.class})
@AutoConfigurationPackage
@Slf4j
public class PersonTests {

	@Autowired
	PersonController personController;
	
	@Autowired
	AddressController addressController;
	
	
	
	
	Integer personId = 0;
	Integer addressId = 0;
	
	
	@Test
	public void testAll() {
		testAddingPerson();
		testAddingAddressToPerson();
		testEditPerson();
		testEditAddress();
		testDeleteAddress();
		testDeletePerson();
	}
	
	public void testAddingPerson() {
		Person personObj = new Person();
		personObj.setFirstName("Mars");
		personObj.setLastName("Compary");
		personObj = personController.addPerson(personObj);
		log.info("PERSON DETAILS ADDED: PERSON ID:: "+personObj.getId());
		personId = personObj.getId();
	}
	
	public void testAddingAddressToPerson() {
		
		Person personObj = new Person();
		personObj.setId(personId);
		List<Address> addresses = new ArrayList<Address>();
		
		Address addressObj1 = new Address();
		Address addressObj2 = new Address();
		
		addressObj1.setAddressType("Home");
		addressObj1.setStreet("Street - 1");
		addressObj1.setCity("Hyderabad");
		addressObj1.setPostalCode("500039");
		
		addressObj2.setAddressType("Office");
		addressObj2.setStreet("Street - 2");
		addressObj2.setCity("Hyderabad");
		addressObj2.setPostalCode("500045");
		
		addresses.add(addressObj1);
		addresses.add(addressObj2);
		
		personObj.setAddresses(addresses);
		log.info(personController.addAddressToPerson(personObj, personId));
	}
	
	public void testEditPerson() {
		Person personObj = personController.getPersonsOnId(personId);
		personObj.setFirstName("EDIT TEST");
		log.info(personController.editPerson(personObj, personId));
	}
	
	public void testEditAddress() {
		Person personObj = personController.getPersonsOnId(personId);
		List<Address> address = personObj.getAddresses();
		Address address1 = address.get(0);
		addressId = address1.getId();
		address1.setCity("Secundrabad");
		log.info(addressController.editAddress(address1, addressId));
	}
	
	public void testDeleteAddress() {
		log.info(addressController.deleteAddress(addressId));
	}
	
	public void testDeletePerson() {
		log.info(personController.deletePerson(personId));
	}
}
