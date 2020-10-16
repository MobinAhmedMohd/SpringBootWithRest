package com.mars.dao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.dao.Address;
import com.mars.dao.Person;
import com.mars.dao.repositories.PersonRepository;
import com.mars.dao.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	
	PersonRepository personRepository;
	
	@Autowired
	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@Override
	public List<Person> getPersonsList() {
		return personRepository.getPersons();
	}

	@Override
	public Person createOrupdatePerson(Person person) {
		if(person.getAddresses() != null) {
			for(Address address:person.getAddresses()) {
				address.setPersonId(person);
			}
		}
		return personRepository.save(person);
	}

	@Override
	public Person getPersonOnId(Integer personId) {
		return personRepository.findOne(personId);
	}

	@Override
	public void deletePerson(Integer personId) {
		personRepository.delete(personId);
		
	}

}
