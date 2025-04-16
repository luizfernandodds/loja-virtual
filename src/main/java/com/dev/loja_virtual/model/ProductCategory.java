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
@Table(name="product_category")
@SequenceGenerator(name = "seq_product_category", sequenceName =  "seq_product_category", initialValue = 1, allocationSize = 1 )
public class ProductCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="pc_id")
	private Long id;
	
	@Column(name = "pc_descrition", nullable = false)
	private String description;
	
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "company_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "product_category_jp_fk"))
	private JuridicPerson company = new JuridicPerson();
}
