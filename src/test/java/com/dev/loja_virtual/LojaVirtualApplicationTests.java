package com.dev.loja_virtual;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dev.loja_virtual.controller.AcessController;
import com.dev.loja_virtual.model.Acess;
import com.dev.loja_virtual.repository.AcessRepository;
import com.dev.loja_virtual.service.AcessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualApplication.class)
class LojaVirtualApplicationTests extends TestCase {
	
	@Autowired
	private AcessService acessService;
	
	@Autowired
	private AcessController acessController;
	
	@Autowired
	private AcessRepository acessRepository;
	
	@Autowired
	private WebApplicationContext wac;
	
	
	@Test
	public void testCadastroAcessoApi() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		
		Acess acess = new Acess();
		
		acess.setDescription("ROLE_COMPRADOR");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		 ResultActions retornoApi =
		 mockMvc.perform(MockMvcRequestBuilders.post("/salvarAcesso")
		 .content(objectMapper.writeValueAsString(acess))
		 .accept("application/json")
		 .contentType("application/json"));
		 
		 System.out.println("Retorno da API: "+ retornoApi.andReturn().getResponse().getContentAsString());
		 
			/*CONVERTER O RETORNO DA API PARA UM OBJETO DE ACESSO */
		 
		 Acess objRetorno = objectMapper.
				 readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acess.class);
		 
		 
		 assertEquals(acess.getDescription(), objRetorno.getDescription());
	}

	@Test
 	public void testCadastraAcesso() {
		
		Acess acess = new Acess();
		
		acess.setDescription("ROLE_ADMIN");
		
		//acessService.save(acess);
		
		
		assertEquals(true, acess.getId() == null);
		
		acess = acessController.saveAcess(acess).getBody();
		
		assertEquals(true, acess.getId() > 0);
		
		assertEquals("ROLE_ADMIN", acess.getDescription());
		
		Acess acess3 = new Acess();
		
		acess3.setDescription("ROLE_ALUNO");
		
		acess3 = acessController.saveAcess(acess3).getBody();
		
		List<Acess> acessos = acessRepository.searchAcess("ALUNO".trim().toUpperCase());
		
		assertEquals(1, acessos.size());
		
		acessRepository.deleteById(acess3.getId());
	}

}
