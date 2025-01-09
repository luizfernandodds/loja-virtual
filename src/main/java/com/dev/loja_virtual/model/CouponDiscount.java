package com.dev.loja_virtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@Column(name = "cod_discount")
	private String codDiscount;
	
	@Column(name = "value_real_discount")
	private BigDecimal valueRealDiscount;
	
	@Column(name = "value_percent_discount")
	private BigDecimal valuePercentageDiscount;
	
	@Column(name = "dt_expiration_discount")
	@Temporal(TemporalType.DATE)
	private Date dtExpirationDiscount;
	

}
