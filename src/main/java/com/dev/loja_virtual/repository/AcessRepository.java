package com.dev.loja_virtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.loja_virtual.model.Acess;

@Repository
@Transactional
public interface AcessRepository extends JpaRepository<Acess, Long> {
	
	//Busca os acessos pela descrição
	@Query("select a from Acess a where upper(trim(a.description)) like %?1%")
	List<Acess> searchAcess (String desc); 
	
	
	//Busca os acessos pela descrição de uma empresa específica
	@Query("select a from Acess a where upper(trim(a.description)) like %?1%and a.company.id = ?2")
	public List<Acess> findAcessByCompany(String desc, Long idCompany);
	
	
	//Busca todo acessos de uma empresa específica
	@Query("select a from Acess a where company.id = ?1")
	public List<Acess> findAllAcessByCompany(Long idCompany);

}
