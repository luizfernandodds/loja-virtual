package com.dev.loja_virtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.dev.loja_virtual.model.Acess;
import com.dev.loja_virtual.service.AcessService;

@Controller
public class AcessController {
	
	@Autowired
	private AcessService acessService;

	@PostMapping("/salvarAcesso")
	public Acess saveAcess(Acess acess) {
		
		return acessService.save(acess);
	}
	
	
	
}
