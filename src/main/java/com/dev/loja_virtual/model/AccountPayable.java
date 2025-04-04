package com.dev.loja_virtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.dev.loja_virtual.enums.StatusAccountPayable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "account_payable")
@SequenceGenerator(name = "seq_account_payable", sequenceName = "seq_account_payable", initialValue = 1, allocationSize = 1)
public class AccountPayable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_account_payable")
	private Long id;
	
	@Column(nullable = false)
	private String description;
	
	@Column(name = "value_total", nullable = false)
	private BigDecimal valueTotal;
	
	@Column(name = "value_discount")
	private BigDecimal valueDiscount;
	
	@Column(name = "status_account_payable", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusAccountPayable statusAccountPayable;
	
	@Column(name = "date_payment")
	@Temporal(TemporalType.DATE)
	private Date datePayment; // Data de pagamento
	
	
	@Column(name = "date_due")
	@Temporal(TemporalType.DATE)
	private Date dateDue; // Data de Vencimento
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "person_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;
	
	
	/*
	 * @ManyToOne(targetEntity = PhysicsPerson.class)
	 * 
	 * @JoinColumn(name = "physics_person_id", nullable = false, foreignKey
	 * = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "physics_person_fk"))
	 * private PhysicsPerson physicsPerson;
	 */
	
	@ManyToOne
	@JoinColumn(name = "supplier_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "supplier_fk"))
	private JuridicPerson supplier; //Fornecedor
	
	
	 @ManyToOne(targetEntity = JuridicPerson.class)
	 @JoinColumn(name = "company_id", nullable = false, foreignKey
	 = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_fk"))
	 private JuridicPerson company = new JuridicPerson(); //EMPRESA
	 	
	
	

}
