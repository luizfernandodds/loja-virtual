package com.dev.loja_virtual.model;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "company_id", nullable = false, foreignKey
	= @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_fk"))
	private JuridicPerson company = new JuridicPerson();

	@JsonIgnore
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.description;
	}



}
