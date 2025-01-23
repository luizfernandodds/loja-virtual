package com.dev.loja_virtual.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "product")
@SequenceGenerator(name = "seq_product", sequenceName = "seq_product", initialValue = 1, allocationSize = 1)
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
	private Long id;
	
	@Column(name = "unit_type", nullable = false)
	private String unitType;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Boolean active = Boolean.TRUE;
	
	@Column(columnDefinition = "text", length = 2000, nullable = false)
	private String description;
	
	@Column(nullable = false)
	private Double weight;
	
	@Column(nullable = false)
	private Double width;
	
	@Column(nullable = false)
	private Double height;
	
	@Column(nullable = false)
	private Double depth;
	
	@Column(name = "selling_price", nullable = false)
	private BigDecimal sellingPrice = BigDecimal.ZERO;
	
	@Column(name = "stock_quantity", nullable = false)
	private Integer stockQuantity = 0;
	
	@Column(name = "alert_stock_quantity")
	private Boolean alertStockQuantity = Boolean.FALSE;
	
	@Column(name = "quantity_alert_stock")
	private Integer quantityAlertStock = 0;
	
	@Column(name = "link_youtube")
	private String linkYoutube;
	
	@Column(name = "quantity_click")
	private Integer quantityClick;
	
	

}
