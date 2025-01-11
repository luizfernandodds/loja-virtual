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
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "product_fk"))
	private Product product;

}
