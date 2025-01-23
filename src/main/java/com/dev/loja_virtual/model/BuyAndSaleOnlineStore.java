package com.dev.loja_virtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import com.dev.loja_virtual.enums.StatusSale;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "buy_and_sale_online_store")
@SequenceGenerator(name = "seq_buy_and_sale_onlie_store", sequenceName = "seq_buy_and_sale_onlie_store", initialValue = 1, allocationSize = 1)
public class BuyAndSaleOnlineStore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_buy_and_sale_onlie_store")
	private Long id;
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "person_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "person_fk"))
	private Person person;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_address_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "delivery_address_fk"))
	private Address deliveryAddress; // Endereço de Entrega
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_address_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "billing_address_fk"))
	private Address billingAddress; // Endereço de Cobrança
	
	@Column(name = "value_total", nullable = false)
	private BigDecimal valueTotal;
	
	@Column(name = "value_discount")
	private BigDecimal valueDiscount;
	
	@ManyToOne
	@JoinColumn(name = "payment_method_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "payment_method_fk"))
	private PaymentMethod paymentMethod;
	
	@JsonIgnoreProperties(allowGetters = true)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sale_nf_id", nullable = true, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "sale_nf_fk"))
	private SaleNF saleNF;
	
	@ManyToOne
	@JoinColumn(name = "coupon_discount_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "coupon_discount_fk"))
	private CouponDiscount couponDiscount;
	
	
	@Column(name = "shipping_cost", nullable = false)
	@Temporal(TemporalType.DATE)
	private BigDecimal shippingCost; // Valor do Frete
	
	@Column(name = "delivery_day", nullable = false)
	private Integer deliveryDay; // Dia da Entrega
	
	
	@Column(name = "billing_date", nullable = false)
	private Integer billingDate; // Data da Venda
	
	@Column(name = "delivery_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date deliveryDate; // Data da Entrega
	
	
	
	
//	@Enumerated(EnumType.STRING)
//	@JoinColumn(name = "status_sale_id", nullable = false)
//	private StatusSale statusSale;
	
	@OneToMany(mappedBy = "buyAndSaleOnlineStore", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SaleItem> saleItems = new ArrayList<>();
	
	@Column(name = "code_label")
	private String codeLabel;
	
	@Column(name = "url_print_label")
	private String urlPrintLabel;
	
	@Column(name = "service_tracking")
	private String serviceTracking;
	
	
}
