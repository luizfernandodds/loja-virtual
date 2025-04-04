package com.dev.loja_virtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Column(name = "number_nf", nullable = false)
	private String numberNF;
	
	@Column(name = "serial_nf", nullable = false)
	private String serialNF;
	
	@Column(name = "description_obs")
	private String descriptionObs;
	
	@Column(name = "value_total", nullable = false)
	private BigDecimal valueTotal;
	
	@Column(name = "value_discount")
	private BigDecimal valueDiscount;
	
	@Column(name = "value_icms", nullable = false)
	private BigDecimal valueICMS;
	
	@Column(name = "date_purchase", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date datePurchase;
	
	/* Campo também usado para o fornecedor do produto */
	//@ManyToOne(targetEntity = JuridicPerson.class)
	//@JoinColumn(name = "juridic_person_id", nullable = false, 
	//foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_id_fk"))
	//private JuridicPerson person;
	
	@ManyToOne(targetEntity = AccountPayable.class)
	@JoinColumn(name = "account_payable_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "account_payable_fk"))
	private AccountPayable accountPayable;
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "person_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT,  name = "person_fk"))
	private Person person;
	
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "company_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_id_fk"))
	private JuridicPerson company;
	

}
