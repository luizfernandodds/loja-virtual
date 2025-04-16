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
@Table(name = "product_item_nf")
@SequenceGenerator(name = "seq_product_item_nf", sequenceName = "seq_product_item_nf" , initialValue = 1, allocationSize = 1)
public class ProductItemNF implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_item_nf")
	private Long id;
	
	@Column(nullable = false)
	private Double quantity;
	
	@ManyToOne
	@JoinColumn(name = "purchase_nf_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "purchase_nf_fk"))
	private PurchaseNF purchaseNF;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pinf_product_fk"))
	private Product product;
	
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "company_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pinf_jp_fk"))
	private JuridicPerson company;

}
