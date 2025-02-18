package com.dev.loja_virtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.loja_virtual.model.Acess;
import com.dev.loja_virtual.repository.AcessRepository;
import com.dev.loja_virtual.service.AcessService;

@Controller
@RestController
public class AcessController {
	
	@Autowired
	private AcessService acessService;
	
	@Autowired
	private AcessRepository acessRepository;

	@ResponseBody //Retorno da API
	@PostMapping(value = "/salvarAcesso") //Mapeando para receber um JSON
	public ResponseEntity<Acess> saveAcess(@RequestBody Acess acess) { // Recebe o JSON e converte para Objeto
		
		Acess acessSaved = acessService.save(acess);
		
		return new ResponseEntity<Acess>(acessSaved, HttpStatus.OK);
	}
	
	@ResponseBody //Retorno da API
	@PostMapping(value = "/deleteAcesso") //Mapeando para receber um JSON
	public ResponseEntity<Acess> deleteAcess(@RequestBody Acess acess) { // Recebe o JSON e converte para Objeto
		
		acessRepository.deleteById(acess.getId());
		
		return new ResponseEntity<Acess>(HttpStatus.OK);
	}
	
	
}
