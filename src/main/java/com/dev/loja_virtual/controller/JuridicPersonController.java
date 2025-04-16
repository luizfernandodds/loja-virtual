package com.dev.loja_virtual.controller;

import java.util.List;

import javax.swing.PopupFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.loja_virtual.exception.ExceptionMsg;
import com.dev.loja_virtual.model.Acess;
import com.dev.loja_virtual.model.JuridicPerson;
import com.dev.loja_virtual.repository.JuridicPersonRepository;
import com.dev.loja_virtual.service.JuridicPersonService;

@RestController
public class JuridicPersonController {
	
	@Autowired
	private JuridicPersonService juridicPersonService;
	
	@Autowired
	private JuridicPersonRepository juridicPersonRepository;

	
	@ResponseBody
	@PostMapping(value = "/savePJ")
	public ResponseEntity<JuridicPerson> savePJ(@RequestBody JuridicPerson juridicPerson) throws ExceptionMsg{
		
		
		if(juridicPerson == null) {
			throw new ExceptionMsg("Pessoa Jurídica não pode ser NULL");
		}
		
		if (juridicPerson.getId() == null && juridicPersonRepository.existsByCnpj(juridicPerson.getCnpj())) {
		    throw new ExceptionMsg("Já existe CNPJ cadastrado com o número: " + juridicPerson.getCnpj());
		}
		
		JuridicPerson saveJuridicPerson = juridicPersonRepository.save(juridicPerson);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saveJuridicPerson);
	}
	
	@ResponseBody
	@GetMapping(value = "**/searchPJ/{name}")
	public ResponseEntity<List<JuridicPerson>> searchPJ(@PathVariable("name") String name) throws ExceptionMsg{
		
		List<JuridicPerson> pj = juridicPersonService.searchPJ(name);
		
		return new ResponseEntity<List<JuridicPerson>>(pj, HttpStatus.OK);
		
	}
	
	@ResponseBody
	@GetMapping(value = "/searchByCNPJ/{cnpj}")
	public ResponseEntity<List<JuridicPerson>> searchByCnpj(@PathVariable("cnpj") String cnpj) throws ExceptionMsg{
		
		List<JuridicPerson> companies = juridicPersonService.searchByCNPJ(cnpj);
		
		return new ResponseEntity<List<JuridicPerson>>(companies, HttpStatus.OK);
	}
}
