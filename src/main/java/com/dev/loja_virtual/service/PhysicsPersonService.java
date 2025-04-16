package com.dev.loja_virtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.loja_virtual.exception.ExceptionMsg;
import com.dev.loja_virtual.model.Address;
import com.dev.loja_virtual.model.JuridicPerson;
import com.dev.loja_virtual.model.PhysicsPerson;
import com.dev.loja_virtual.repository.JuridicPersonRepository;
import com.dev.loja_virtual.repository.PhysicsPersonRepository;

@Service
public class PhysicsPersonService {

	@Autowired
	private PhysicsPersonRepository physicsPersonRepository;

	@Autowired
	private JuridicPersonRepository juridicPersonRepository;

	public PhysicsPerson save(PhysicsPerson physicsPerson) throws ExceptionMsg {

		if (physicsPerson.getListAddress() != null) {
			for (Address address : physicsPerson.getListAddress()) {
				address.setPerson(physicsPerson);

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

		return physicsPersonRepository.save(physicsPerson);
	}
	
	public List<PhysicsPerson> searchPersonByCPF(String cpf) throws ExceptionMsg{
		
		List<PhysicsPerson> persons = physicsPersonRepository.findByCpf(cpf);
		
		if(persons == null || persons.isEmpty()) {
			throw new ExceptionMsg("CPF não encontrado! ");
		}
		
		return persons;
	}
	
	
	public List<PhysicsPerson> searchPerson(String name) throws ExceptionMsg{
		
		List<PhysicsPerson> pfList = physicsPersonRepository.findByName(name);
		
		if(pfList == null || pfList.isEmpty()) {
			throw new ExceptionMsg("Pessoa não encontrada!");
		}
		
		return pfList;
	}
}
