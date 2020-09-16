package com.mars.dao.service;

import java.util.List;

import com.mars.dao.Person;

public interface PersonService {

	public List<Person> getPersonsList();
	
	public Person createOrupdatePerson(Person person);
	
	public Person getPersonOnId(Integer personId);
	
	public void deletePerson(Integer personId);
	
	
}
