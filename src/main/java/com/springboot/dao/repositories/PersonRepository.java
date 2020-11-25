package com.springboot.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.dao.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	@Query(value = "SELECT person FROM Person AS person")
	List<Person> getPersons();
	
	
}
