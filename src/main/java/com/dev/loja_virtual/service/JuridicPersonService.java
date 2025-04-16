package com.dev.loja_virtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.loja_virtual.exception.ExceptionMsg;
import com.dev.loja_virtual.model.Address;
import com.dev.loja_virtual.model.JuridicPerson;
import com.dev.loja_virtual.model.PhysicsPerson;
import com.dev.loja_virtual.repository.JuridicPersonRepository;

@Service
public class JuridicPersonService {

	@Autowired
	private JuridicPersonRepository juridicPersonRepository;

	public JuridicPerson save(JuridicPerson juridicPerson) throws ExceptionMsg {

		if (juridicPerson.getListAddress() != null) {
			for (Address address : juridicPerson.getListAddress()) {
				address.setPerson(juridicPerson);

				System.out.println("Empresa ID: "
						+ (address.getAddressCompany() != null ? address.getAddressCompany().getId() : "null"));

				if (address.getAddressCompany() != null && address.getAddressCompany().getId() != null) {
					JuridicPerson pj = juridicPersonRepository.findById(address.getAddressCompany().getId())
							.orElseThrow(() -> new ExceptionMsg(
									"Empresa não encontrada com o ID: " + address.getAddressCompany().getId()));
					address.setAddressCompany(pj);
				} else {
					throw new ExceptionMsg("O Endereço deve conter uma empresa válida.");
				}
			}
		}

		return juridicPersonRepository.save(juridicPerson);
	}
	
	public List<JuridicPerson> searchPJ(String name) throws ExceptionMsg {
		
		List<JuridicPerson> pj = juridicPersonRepository.findByName(name);
		
		
		if(pj == null || pj.isEmpty()) {
			throw new ExceptionMsg("Empresa não encontrada!");
			
		}
		
		return pj;
		
	}
	
	public List<JuridicPerson> searchByCNPJ(String cnpj) throws ExceptionMsg{
		
		List<JuridicPerson> companies = juridicPersonRepository.findByCnpj(cnpj);
		
		if(companies == null || companies.isEmpty()) {
			throw new ExceptionMsg("Empresa não encontrada!");
		}
		
		return companies;
	}

}
