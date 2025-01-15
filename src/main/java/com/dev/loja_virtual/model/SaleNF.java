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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "sale_nf")
@SequenceGenerator(name = "seq_sale_nf", sequenceName = "seq_sale_nf", initialValue = 1, allocationSize = 1)
public class SaleNF implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sale_nf")
	private Long id;
	
	@Column(nullable = false)
	private String number;
	
	@Column(nullable = false)
	private String serial;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private String key;
	
	@Column(columnDefinition = "text", nullable = false)
	private String xml;
	
	@Column(columnDefinition = "text", nullable = false)
	private String pdf;
	
	@OneToOne
	@JoinColumn(name="buy_and_sale_online_store_id", nullable = true,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "buy_and_sale_online_store_fk"))
	private BuyAndSaleOnlineStore buyAndSaleOnlineStore;
	
	
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "company_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_fk"))
	private JuridicPerson company;
}
