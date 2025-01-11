package com.dev.loja_virtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "purchase_nf")
@SequenceGenerator(name = "seq_purchase_nf", sequenceName = "seq_purchase_nf", initialValue = 1, allocationSize = 1)
public class PurchaseNF implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_purchase_nf")
	private Long id;
	
	@Column(name = "number_nf")
	private String numberNF;
	
	@Column(name = "description_obs")
	private String descriptionObs;
	
	@Column(name = "value_total")
	private BigDecimal valueTotal;
	
	@Column(name = "value_discount")
	private BigDecimal valueDiscount;
	
	@Column(name = "value_icms")
	private BigDecimal valueICMS;
	
	@Column(name = "date_purchase")
	@Temporal(TemporalType.DATE)
	private Date datePurchase;
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "person_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;
	
	@ManyToOne(targetEntity = AccountPayable.class)
	@JoinColumn(name = "account_payable_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "account_payable_fk"))
	private AccountPayable accountPayable;
	

}
