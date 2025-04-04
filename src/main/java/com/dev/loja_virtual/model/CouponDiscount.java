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
@Table(name = "coupon_discount")
@SequenceGenerator(name = "seq_coupon_discount",  sequenceName = "seq_coupon_discount", initialValue = 1, allocationSize = 1)
public class CouponDiscount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_coupon_discount")
	private Long id;
	
	@Column(name = "cod_discount", nullable = false)
	private String codDiscount;
	
	@Column(name = "value_real_discount")
	private BigDecimal valueRealDiscount;
	
	@Column(name = "value_percent_discount")
	private BigDecimal valuePercentageDiscount;
	
	@Column(name = "dt_expiration_discount", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtExpirationDiscount;
	
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "company_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_fk"))
	private JuridicPerson company = new JuridicPerson(); //EMPRESA
	

}
