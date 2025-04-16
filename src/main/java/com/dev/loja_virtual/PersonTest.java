package com.dev.loja_virtual;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dev.loja_virtual.model.JuridicPerson;
import com.dev.loja_virtual.model.PhysicsPerson;
import com.dev.loja_virtual.repository.JuridicPersonRepository;
import com.dev.loja_virtual.repository.PhysicsPersonRepository;
import com.dev.loja_virtual.service.JuridicPersonService;
import com.dev.loja_virtual.service.PhysicsPersonService;

//@Component
public class PersonTest implements CommandLineRunner {
	@Autowired
	private PhysicsPersonService physicsPersonService;

	@Autowired
	private JuridicPersonService juridicPersonService;
	
	@Autowired
	private PhysicsPersonRepository physicsPersonRepository;
	
	@Autowired
	private JuridicPersonRepository juridicPersonRepository;


	@Override
	public void run(String... args) throws Exception {
		
		Optional<PhysicsPerson> pFisica = physicsPersonRepository.findById(3L); 

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// Criando a pessoa física (representante)
		PhysicsPerson pessoa = new PhysicsPerson();
		pessoa.setName("João Victor da Silva");
		pessoa.setEmail("joao@email.com");
		pessoa.setPhone("67998771540");
		pessoa.setCpf("123.456.789-02");
		pessoa.setDateBirth(sdf.parse("1990-12-25"));

		// Salvando a pessoa física
		//physicsPersonRepository.save(pessoa);

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
		//juridicPersonRepository.save(empresa);

		//List<JuridicPerson> resultado = juridicPersonRepository.findByPhysicsPersonId(pessoa.getId());
		
	}
	
	
	@Transactional
	public void criarOutraEmpresaParaPessoa(Long pessoaFisicaId) {
	    // Buscar a pessoa física já existente
	    PhysicsPerson pessoaFisica = physicsPersonRepository.findById(3L).orElseThrow(() -> new RuntimeException("Pessoa Física não encontrada."));

	    // Criar nova empresa
	    JuridicPerson novaEmpresa = new JuridicPerson();
	    novaEmpresa.setCnpj("12345678000199");
	    novaEmpresa.setName("Nova Empresa Exemplo Ltda");
	    novaEmpresa.setRepresentative(pessoaFisica); // Associação com a mesma pessoa física

	    // Salvar empresa
	    //juridicPersonRepository.save(novaEmpresa);
	}
	
}
