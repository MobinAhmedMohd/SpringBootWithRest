package com.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dao.Person;
import com.springboot.service.PersonService;

@RestController
@Service
public class PersonController {
	
	@Autowired
	PersonService personService;

	@RequestMapping(value = "/getPersons", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<Person> getPersonsList(){
		return personService.getPersonsList();
		
	}
	
	@RequestMapping(value = "/getPersonOnId/{personId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Person getPersonsOnId(@PathVariable Integer personId){
		return personService.getPersonOnId(personId);
		
	}
	
	@RequestMapping(value = "/addPerson",  method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person addPerson(@RequestBody Person person){
		try {
			return personService.createOrupdatePerson(person);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
		return null;
	}
	
	@RequestMapping(value = "/editPerson/{personId}",  method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String editPerson(@RequestBody Person person, @PathVariable Integer personId){
		String retStatement = "";
		try {
			if(personId != null && person.getId() !=null && personId.equals(person.getId())) {
				Person personTemp = personService.getPersonOnId(personId);
				if(personTemp == null) {
					retStatement = "PERSON ID NOT FOUND";
				}else {
					personService.createOrupdatePerson(person);
					retStatement = "SUCESSFULLY EDIT PERSON DETAILS";
				}
			}else {
				retStatement = "NOT EDITED. MAY BE BODY PERSON_ID AND PARAMETER PERSON_ID NOT MATCHING";
			}
		}catch(Exception e) {
			e.printStackTrace();
			retStatement = "ERROR WHILE EDITING";
		}
		
		
		return retStatement;
	}
	
	@RequestMapping(value = "/deletePerson/{personId}",  method = RequestMethod.DELETE)
	public String deletePerson(@PathVariable Integer personId){
		String retStatement = "";
		try {
			if(personId != null ) {
				Person personTemp = personService.getPersonOnId(personId);
				if(personTemp == null) {
					retStatement = "PERSON ID NOT FOUND";
				}else {
					personService.deletePerson(personId);
					retStatement = "PERSON ID DELETED";
				}
			}else {
				retStatement = "PERSON_ID CANNOT BE NULL";
			}
		}catch(Exception e) {
			e.printStackTrace();
			retStatement = "ERROR WHILE DELETING";
		}
		
		
		return retStatement;
	}
	
	@RequestMapping(value = "/addAddress/{personId}",  method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addAddressToPerson(@RequestBody Person person, @PathVariable Integer personId){
		String retStatement = "";
		try {
			if(personId != null && person.getId() !=null && personId.equals(person.getId())) {
				Person personTemp = personService.getPersonOnId(personId);
				if(personTemp == null) {
					retStatement = "PERSON ID NOT FOUND";
				}else {
					personService.createOrupdatePerson(person);
					retStatement = "SUCESSFULLY Added";
				}
			}else {
				retStatement = "ADDRESS NOT ADDED. MAY BE BODY PERSON_ID AND PARAMETER PERSON_ID NOT MATCHING";
			}
		}catch(Exception e) {
			e.printStackTrace();
			retStatement = "ERROR WHILE Adding Address";
		}
		
		
		return retStatement;
	}
}
