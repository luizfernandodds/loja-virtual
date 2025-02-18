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
	
	@Query("select a from Acess a where upper(trim(a.description)) like %?1%")
	List<Acess> searchAcess (String desc); 

}
