package com.dev.loja_virtual.model;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of="id")
@Entity
@Table(name="acess")
@SequenceGenerator(name = "seq_acess", sequenceName = "seq_acess", initialValue = 1, allocationSize = 1)
public class Acess implements GrantedAuthority {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acess")
	private Long id;
	
	@Column(nullable = false)
	private String description; // Acess Ex > ROLE_ADMIN > ROLE_SYSADMIN > ROLE_CLIENT
	
	/*
	 * @ManyToOne(targetEntity = JuridicPerson.class)
	 * 
	 * @JoinColumn(name = "company_id", nullable = false, foreignKey
	 * = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_fk"))
	 * private JuridicPerson company = new JuridicPerson();
	 */

	@JsonIgnore
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.description;
	}



}
