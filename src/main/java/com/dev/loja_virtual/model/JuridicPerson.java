package com.dev.loja_virtual.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
	
	private String cnpj;
	
	@Column(name = "state_registration")
	private String stateRegistration; // Inscrição Estadual
	
	@Column(name = "municipal_registration")
	private String municipalRegistration; // Inscrição Municipal
	
	@Column(name = "trade_name")
	private String tradeName; // Nome Fantasia
	
	@Column(name = "company_name")
	private String companyName; // Razão Social
	
	private String category;
}
