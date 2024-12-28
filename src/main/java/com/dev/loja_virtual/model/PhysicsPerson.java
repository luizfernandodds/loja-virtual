package com.dev.loja_virtual.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "physics_person")
public class PhysicsPerson extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cpf;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_birth")
	private Date dateBirth;
}
