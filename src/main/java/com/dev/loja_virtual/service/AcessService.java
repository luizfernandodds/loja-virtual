package com.dev.loja_virtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.loja_virtual.model.Acess;
import com.dev.loja_virtual.repository.AcessRepository;

@Service
public class AcessService {

	@Autowired
	private AcessRepository acessRepository;
	
	
	public Acess save(Acess acess) {
		return acessRepository.save(acess);
	}
}
