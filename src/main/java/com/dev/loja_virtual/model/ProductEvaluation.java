package com.dev.loja_virtual.model;

import java.io.Serializable;

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
	
	@ManyToOne(targetEntity = PhysicsPerson.class)
	@JoinColumn(name = "physics_person_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "physics_person_fk"))
	private PhysicsPerson physicsPerson;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "product_fk"))
	private Product product;
	
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "juric_person_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "juridic_person_fk"))
	private JuridicPerson company;
	
}
