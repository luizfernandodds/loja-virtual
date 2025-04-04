package com.dev.loja_virtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.loja_virtual.exception.ExceptionMsg;
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
	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping(value = "**/salvarAcesso") /*Mapeando a url para receber JSON*/
	public ResponseEntity<Acess> salvarAcesso(@RequestBody Acess acess) throws ExceptionMsg  { /*Recebe o JSON e converte pra Objeto*/
		
		if (acess.getId() == null) {
		  List<Acess> accesses = acessRepository.searchAcess(acess.getDescription().toUpperCase());
		  
		  if (!accesses.isEmpty()) {
			  throw new ExceptionMsg("Já existe Acesso com a descrição: " + acess.getDescription());
		  }
		}
		
		
		Acess acessSave = acessService.save(acess);
		
		return new ResponseEntity<Acess>(acessSave, HttpStatus.OK);
	}
	
	@ResponseBody //Retorno da API
	@PostMapping(value = "/deleteAcesso") //Mapeando para receber um JSON
	public ResponseEntity<Acess> deleteAcess(@RequestBody Acess acess) { // Recebe o JSON e converte para Objeto
		
		acessRepository.deleteById(acess.getId());
		
		return new ResponseEntity<Acess>(HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "/deleteAcessById")
	public ResponseEntity<Acess> deleteAcessById(@RequestBody Acess acess){
		
		
		acessRepository.deleteById(acess.getId());
		
		return new ResponseEntity<Acess>(HttpStatus.OK);
		
	}
	
	
	@ResponseBody
	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<Acess> findById(@PathVariable("id") Long id) throws ExceptionMsg{
		
		Acess acess = acessRepository.findById(id).orElse(null);
		
		if(acess == null) {
			throw new ExceptionMsg("ID " +id + " não encontrado!");
		}
		
		
		return new ResponseEntity<Acess>(acess, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/findByDesc/{desc}")
	public ResponseEntity<List<Acess>> buscarPorDesc(@PathVariable("desc") String desc) { 
		
		List<Acess> acess = acessRepository.searchAcess(desc.toUpperCase());
		
		return new ResponseEntity<List<Acess>>(acess,HttpStatus.OK);
	}
	
	
}
