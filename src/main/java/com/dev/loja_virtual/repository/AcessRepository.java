package com.dev.loja_virtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dev.loja_virtual.model.Acess;

@Repository
@Transactional
public interface AcessRepository extends JpaRepository<Acess, Long> {

}
