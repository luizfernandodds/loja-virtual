package com.dev.loja_virtual.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.loja_virtual.exception.ExceptionMsg;
import com.dev.loja_virtual.model.Person;
import com.dev.loja_virtual.repository.JuridicPersonRepository;
import com.dev.loja_virtual.repository.PhysicsPersonRepository;

@RestController
public class PersonController {
	
	@Autowired
	PhysicsPersonRepository physicsPersonRepository;
	
	@Autowired
	JuridicPersonRepository juridicPersonRepository;
	

	@ResponseBody
    @GetMapping(value = "/findAllPersonsByName/{name}")
    public ResponseEntity<List<Person>> findAllPersonsByName(@PathVariable("name") String name) throws ExceptionMsg {

        List<Person> result = new ArrayList<>();

		result.addAll(physicsPersonRepository.findAllByName(name));
        result.addAll(juridicPersonRepository.findAllByName(name));

        if (result.isEmpty()) {
            throw new ExceptionMsg("Nenhum registro encontrado com o nome: " + name);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
