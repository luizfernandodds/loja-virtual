package com.dev.loja_virtual.model;

import java.io.Serializable;

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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "product_evaluation")
@SequenceGenerator(name = "seq_product_evaluation", sequenceName = "seq_product_evaluation", initialValue = 1, allocationSize = 1)
public class ProductEvaluation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_evaluation")
	private Long id;

	@Column(nullable = false)
	private Integer notes;

	@Column(nullable = false)
	private String description;

	/*
	 * @ManyToOne(targetEntity = PhysicsPerson.class)
	 * 
	 * @JoinColumn(name = "physics_person_id", nullable = false, foreignKey
	 * = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "physics_person_fk"))
	 * private PhysicsPerson physicsPerson;
	 */

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "product_fk"))
	private Product product;

	
	 @ManyToOne(targetEntity = JuridicPerson.class)
	 @JoinColumn(name = "company_id", nullable = false, 
	 foreignKey	 = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_fk"))
	 private JuridicPerson company;
	 

	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;

}
