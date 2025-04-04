package com.dev.loja_virtual.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dev.loja_virtual.model.SystemUser;

public interface SystemUserRepository extends CrudRepository<SystemUser, Long> {
	
	
	@Query("select u from SystemUser u where u.login = ?1")
	SystemUser findUserByLogin(String login);
}
