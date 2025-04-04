package com.dev.loja_virtual.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Column(nullable = false)
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_birth")
	private Date dateBirth;
}
