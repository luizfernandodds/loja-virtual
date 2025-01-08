package com.dev.loja_virtual.model;

import com.dev.loja_virtual.enums.AddressType;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of="id")
@Entity
@SequenceGenerator(name = "seq_address", sequenceName = "seq_address", initialValue = 1, allocationSize = 1)
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address")
	private Long id;
	
	
	private String street;
	
	private String cep;
	
	private String number;
	
	private String complement;
	
	private String district; // bairro
	
	private String uf;
	
	private String city;
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name="person_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;
	
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
	
}
