package com.dev.loja_virtual;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.dev.loja_virtual.model.JuridicPerson;
import com.dev.loja_virtual.model.PhysicsPerson;
import com.dev.loja_virtual.repository.JuridicPersonRepository;
import com.dev.loja_virtual.repository.PhysicsPersonRepository;
import com.dev.loja_virtual.service.JuridicPersonService;
import com.dev.loja_virtual.service.PhysicsPersonService;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class PersonTests {

	@Autowired
	private PhysicsPersonRepository physicsPersonRepository;
	
	@Autowired
	private JuridicPersonRepository juridicPersonRepository;
	
	
	@Autowired
	private PhysicsPersonService physicsPersonService;
	
	@Autowired
	private JuridicPersonService juridicPersonService;
	
	 @PersistenceContext
	    private EntityManager entityManager;
	 
	 
	 	
	    
	    @Test
	    public void testBuscarEmpresasPorRepresentante() {
	        Optional<PhysicsPerson> pessoaFisica = physicsPersonRepository.findById(3L);

	        if (pessoaFisica.isPresent()) {
	            PhysicsPerson pf = pessoaFisica.get();
	            List<JuridicPerson> empresas = juridicPersonRepository.findJuridPersonByRepresentativeId(pf.getId());

	            empresas.forEach(jp -> System.out.println("Empresa: " + jp.getCompanyName()));
	        } else {
	            System.out.println("Pessoa física com ID 3 não encontrada.");
	        }
	    }
	
	
	@Test
	void salvarPessoaFisicaComEmpresaVinculada() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// Criando a pessoa física (representante)
		PhysicsPerson pessoa = new PhysicsPerson();
		pessoa.setName("João Victor da Silva");
		pessoa.setEmail("joao@email.com");
		pessoa.setPhone("67998771540");
		pessoa.setCpf("123.456.789-02");
		pessoa.setDateBirth(sdf.parse("1990-12-25"));

		// Salvando a pessoa física
		physicsPersonRepository.save(pessoa);

		// Criando a empresa (jurídica)
		JuridicPerson empresa = new JuridicPerson();
		empresa.setName("Empresa Teste");
		empresa.setEmail("empresa@email.com");
		empresa.setPhone("6799123322");
		empresa.setCnpj("00.000.000/0001-00");
		empresa.setCompanyName("Empresa Teste LTDA");
		empresa.setTradeName("Empresa Teste");
		empresa.setCategory("Comércio");
		empresa.setStateRegistration("ISENTO");
		empresa.setMunicipalRegistration("123456");

		// Associando o representante
		empresa.setRepresentative(pessoa);

		// Salvando a empresa
		juridicPersonRepository.save(empresa);
	     
	     //List<JuridicPerson> resultado = juridicPersonRepository.findByPhysicsPersonId(pessoa.getId());
	     
	     //assertFalse(resultado.isEmpty());
	     //assertEquals("Empresa Teste", resultado.get(0).getName());
	     
		
	}
}
