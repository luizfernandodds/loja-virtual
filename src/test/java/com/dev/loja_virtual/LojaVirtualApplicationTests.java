package com.dev.loja_virtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dev.loja_virtual.model.Acess;
import com.dev.loja_virtual.repository.AcessRepository;
import com.dev.loja_virtual.service.AcessService;

@SpringBootTest(classes = LojaVirtualApplication.class)
class LojaVirtualApplicationTests {
	
	@Autowired
	private AcessService acessService;
	
	//@Autowired
	//private AcessRepository acessRepository;

	@Test
	public void testCadastraAcesso() {
		
		Acess acess = new Acess();
		
		acess.setDescription("ROLE_ADMIN");
		
		acessService.save(acess);
		
	}

}
