package com.dev.loja_virtual.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "juridic_person")
public class JuridicPerson extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String cnpj;
	
	@Column(name = "state_registration", nullable = false)
	private String stateRegistration; // Inscrição Estadual
	
	@Column(name = "municipal_registration")
	private String municipalRegistration; // Inscrição Municipal
	
	@Column(name = "trade_name", nullable = false)
	private String tradeName; // Nome Fantasia
	
	@Column(name = "company_name", nullable = false)
	private String companyName; // Razão Social
	
	private String category;
}
