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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationMain.class})
@AutoConfigurationPackage
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
		testEditAddress();
		testDeleteAddress();
		testDeletePerson();
	}
	
	public void testAddingPerson() {
		Person personObj = new Person();
		personObj.setFirstName("Mars");
		personObj.setLastName("Compary");
		personObj = personController.addPerson(personObj);
		System.out.println("PERSON DETAILS ADDED: PERSON ID:: "+personObj.getId());
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
		System.out.println(personController.addAddressToPerson(personObj, personId));
	}
	
	public void testEditAddress() {
		Person personObj = personController.getPersonsOnId(personId);
		List<Address> address = personObj.getAddresses();
		Address address1 = address.get(0);
		addressId = address1.getId();
		address1.setCity("Secundrabad");
		System.out.println(addressController.editAddress(address1, addressId));
	}
	
	public void testDeleteAddress() {
		System.out.println(addressController.deleteAddress(addressId));
	}
	
	public void testDeletePerson() {
		System.out.println(personController.deletePerson(personId));
	}
}
