package com.dev.loja_virtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "company_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_fk"))
	private JuridicPerson company;
	
	@ManyToOne(targetEntity = ProductCategory.class)
	@JoinColumn(name = "product_category_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "product_category_fk"))
	private ProductCategory productCategory = new ProductCategory();
	
	@ManyToOne(targetEntity = ProductBrand.class)
	@JoinColumn(name = "product_brand_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "product_brand_fk"))
	private ProductBrand productBrand = new ProductBrand();
	
	@OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProductImage> imagesProduct = new ArrayList<>();
	
	

}
