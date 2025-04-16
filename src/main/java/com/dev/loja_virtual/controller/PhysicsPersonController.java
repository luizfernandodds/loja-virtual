package com.dev.loja_virtual.controller;

import java.util.List;

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
import com.dev.loja_virtual.model.PhysicsPerson;
import com.dev.loja_virtual.repository.JuridicPersonRepository;
import com.dev.loja_virtual.repository.PhysicsPersonRepository;
import com.dev.loja_virtual.service.PhysicsPersonService;

@RestController
public class PhysicsPersonController {

	@Autowired
	PhysicsPersonRepository physicsPersonRepository;

	@Autowired
	JuridicPersonRepository juridicPersonRepository;
	
	@Autowired
	PhysicsPersonService physicsPersonService;

	@PostMapping(value = "/savePF")
	public ResponseEntity<PhysicsPerson> savePF(@RequestBody PhysicsPerson physicsPerson) throws ExceptionMsg {

		PhysicsPerson savePerson = physicsPersonService.save(physicsPerson);

		return ResponseEntity.status(HttpStatus.CREATED).body(savePerson);
	}
	
	@ResponseBody
	@GetMapping(value = "**/searchByCPF/{cpf}")
	public ResponseEntity<List<PhysicsPerson>> searchByCPF(@PathVariable("cpf") String cpf) throws ExceptionMsg {
		
		List<PhysicsPerson> fisicas = physicsPersonService.searchPersonByCPF(cpf);
		
		
		return new ResponseEntity<List<PhysicsPerson>>(fisicas, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/searchPF/{name}")
	public ResponseEntity<List<PhysicsPerson>> searchPF(@PathVariable("name") String name) throws ExceptionMsg{
		
		List<PhysicsPerson> pfList = physicsPersonService.searchPerson(name);
		
		return new ResponseEntity<List<PhysicsPerson>>(pfList, HttpStatus.OK);
		
	}
	

}
