package com.dev.loja_virtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.loja_virtual.model.JuridicPerson;

public interface JuridicPersonRepository extends JpaRepository<JuridicPerson, Long> {

	
	@Query("SELECT j FROM JuridicPerson j WHERE j.name like %?1%")
	public List<JuridicPerson> findByName(String name);
	
	@Query("SELECT j FROM JuridicPerson j WHERE j.representative.id = ?1")
	public List<JuridicPerson> findJuridPersonByRepresentativeId(Long idRepresentative);
	
	
	public List<JuridicPerson> findByCnpj(String cnpj);

	
	public Boolean existsByCnpj(String cnpj);
	
	@Query("SELECT j FROM JuridicPerson j WHERE LOWER(j.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<JuridicPerson> findAllByName(String name);
	
	

}
