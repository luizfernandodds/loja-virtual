package com.dev.loja_virtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dev.loja_virtual.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
	
	
	@Query("SELECT p FROM Person p WHERE p.name like %?1%")
	public List<Person> findAllByName(String name);

}
