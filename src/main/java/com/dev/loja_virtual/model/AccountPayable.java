package com.dev.loja_virtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.dev.loja_virtual.enums.StatusAccountPayable;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	
	@ManyToOne(targetEntity = PhysicsPerson.class)
	@JoinColumn(name = "physics_person_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "physics_person_fk"))
	private PhysicsPerson physicsPerson;
	
	@ManyToOne
	@JoinColumn(name = "person_supplier_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_supplier_fk"))
	private Person personSupplier; //Fornecedor
	
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "company_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_fk"))
	private JuridicPerson company = new JuridicPerson(); //EMPRESA
	
	
	

}
