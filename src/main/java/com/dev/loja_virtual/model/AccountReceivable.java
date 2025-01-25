package com.dev.loja_virtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.dev.loja_virtual.enums.StatusAccountReceivable;

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
@Table(name = "account_receivable")
@SequenceGenerator(name = "seq_account_receivable", sequenceName = "seq_account_receivable", initialValue = 1, allocationSize = 1)
public class AccountReceivable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_account_receivable")
	private Long id;
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;
	
	@Column(nullable = false)
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_account_receivable", nullable = false)
	private StatusAccountReceivable statusAccountReceivable;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_due", nullable = false)
	private Date dateDue; // Data do Vencimento
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_payment")
	private Date datePayment; // Data do Pagamento
	
	
	@Column(name = "value_total", nullable = false)
	private BigDecimal valueTotal;
	
	@Column(name = "value_discount")
	private BigDecimal valueDiscount;
	
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "company_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_fk"))
	private JuridicPerson company = new JuridicPerson(); //EMPRESA
	
	
	

	
}
