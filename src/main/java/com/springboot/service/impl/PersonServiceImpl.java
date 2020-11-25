package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.Address;
import com.springboot.dao.Person;
import com.springboot.dao.repositories.PersonRepository;
import com.springboot.service.PersonService;

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
