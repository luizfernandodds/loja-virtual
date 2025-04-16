package com.dev.loja_virtual.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dev.loja_virtual.model.PhysicsPerson;

public interface PhysicsPersonRepository extends CrudRepository<PhysicsPerson, Long> {
	
	@Query("SELECT p FROM PhysicsPerson p WHERE p.name like %?1%")
	public List<PhysicsPerson> findByName(String name);
	
	
	@EntityGraph(attributePaths = "juridicCompanies")
    Optional<PhysicsPerson> findJuridiPersonById(Long id);
	
	
	public List<PhysicsPerson> findByCpf(String cpf);
	
	@Query("SELECT p FROM PhysicsPerson p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<PhysicsPerson> findAllByName(String name);
	
	
	
	
}
