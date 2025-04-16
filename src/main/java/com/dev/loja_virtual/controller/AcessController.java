package com.dev.loja_virtual.controller;

import java.util.List;
import java.util.Map;

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
import com.dev.loja_virtual.model.JuridicPerson;
import com.dev.loja_virtual.repository.AcessRepository;
import com.dev.loja_virtual.repository.JuridicPersonRepository;
import com.dev.loja_virtual.service.AcessService;
import com.google.gson.Gson;

@Controller
@RestController
public class AcessController {
	
	@Autowired
	private AcessService acessService;
	
	@Autowired
	private AcessRepository acessRepository;
	
	@Autowired
	private JuridicPersonRepository juridicPersonRepository;
	
	@ResponseBody
	@PostMapping(value = "/salvarAcesso")
	public ResponseEntity<?> salvar(@RequestBody Acess acess) {
	    try {
	        if (acess.getCompany() == null || acess.getCompany().getId() == null) {
	            return ResponseEntity.badRequest().body(Map.of(
	                "error", "O campo 'company.id' é obrigatório.",
	                "code", "COMPANY_ID_MISSING"
	            ));
	        }

	        Long companyId = acess.getCompany().getId();

	        JuridicPerson empresa = juridicPersonRepository.findById(companyId)
	            .orElseThrow(() -> new RuntimeException("Empresa com ID " + companyId + " não encontrada"));

	        acess.setCompany(empresa);

	        Acess salvo = acessRepository.save(acess);
	        return ResponseEntity.ok(salvo);

	    } catch (Exception e) {
	        e.printStackTrace(); // Isso vai mostrar o stack trace no console
	        return ResponseEntity.badRequest().body(Map.of(
	            "error", "Erro ao salvar acesso: " + e.getMessage(),
	            "cause", e.getCause() != null ? e.getCause().toString() : "Sem causa adicional",
	            "exception", e.getClass().getName()
	        ));
	    }
	}

	
	@ResponseBody //Retorno da API
	@PostMapping(value = "/deleteAcesso") //Mapeando para receber um JSON
	public ResponseEntity<Acess> deleteAcess(@RequestBody Acess acess) { // Recebe o JSON e converte para Objeto
		
		acessRepository.deleteById(acess.getId());
		
		return new ResponseEntity<Acess>(HttpStatus.OK);
	}
	
	
	@ResponseBody
	@PostMapping(value = "/deleteAcessById/{id}")
	public ResponseEntity<String> deleteAcessById(@RequestBody Acess acess) throws ExceptionMsg{
		
		if(acess.getId() == null) {
			throw new ExceptionMsg("ID " +acess.getId()+ " não encontrado!" );
		}
		
		acessRepository.deleteById(acess.getId());
		
		return new ResponseEntity<String>( new Gson().toJson("Acesso removido!"), HttpStatus.OK);
		
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
