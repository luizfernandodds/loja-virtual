package com.dev.loja_virtual.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
	
	@ManyToOne(optional = true)
	@JsonBackReference
	@JoinColumn(name = "representative_id", nullable = false)
    private PhysicsPerson representative; // Sócio responsável, opcional

    @OneToMany(mappedBy = "company")
    private List<Person> people = new ArrayList<>();
	
}
