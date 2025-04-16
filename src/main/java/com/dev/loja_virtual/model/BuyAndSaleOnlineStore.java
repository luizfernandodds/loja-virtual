package com.dev.loja_virtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dev.loja_virtual.enums.StatusSale;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "bs_person_fk"))
	private Person person;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_address_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "bs_delivery_address_fk"))
	private Address deliveryAddress; // Endereço de Entrega
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_address_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "bs_billing_address_fk"))
	private Address billingAddress; // Endereço de Cobrança
	
	@Column(name = "value_total", nullable = false)
	private BigDecimal valueTotal;
	
	@Column(name = "value_discount")
	private BigDecimal valueDiscount;
	
	@ManyToOne
	@JoinColumn(name = "payment_method_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "bs_payment_method_fk"))
	private PaymentMethod paymentMethod;
	
	@JsonIgnoreProperties(allowGetters = true)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sale_nf_id", nullable = true, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "bs_sale_nf_fk"))
	private SaleNF saleNF;
	
	@ManyToOne
	@JoinColumn(name = "coupon_discount_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "bs_coupon_discount_fk"))
	private CouponDiscount couponDiscount;
	
	
	@Column(name = "shipping_cost", nullable = false)
	private BigDecimal shippingCost; // Valor do Frete
	
	@Column(name = "delivery_day", nullable = false)
	private Integer deliveryDay; // Dia da Entrega
	
	
	@Column(name = "billing_date", nullable = false)
	private Integer billingDate; // Data da Venda
	
	@Column(name = "delivery_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date deliveryDate; // Data da Entrega
	
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "company_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "bs_jp_fk"))
	private JuridicPerson company;
	
	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "status_sale_id", nullable = false)
	private StatusSale statusSale;
	
	@OneToMany(mappedBy = "buyAndSaleOnlineStore", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<SaleItem> saleItems = new ArrayList<>();
	
	@Column(name = "code_label")
	private String codeLabel; // Codigo Etiqueta
	
	@Column(name = "url_print_label")
	private String urlPrintLabel; // URL imprimir Etiqueta
	
	/* Frete que foi escolhido pelo cliente no momento da compra */
	@Column(name = "service_tracking")
	private String serviceTracking;
	
	
}
