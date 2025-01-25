package com.dev.loja_virtual.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tracking_status")
@SequenceGenerator(name = "seq_tracking_status", sequenceName = "seq_tracking_status", initialValue = 1, allocationSize = 1)
public class TrackingStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tracking_status")
	private Long id;
	
	@Column(name = "url_tracking")
	private String urlTracking;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "buy_and_sale_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "buy_and_sale_fk"))
	private BuyAndSaleOnlineStore buyAndSaleOnlineStore;
	
	@JsonIgnore
	@ManyToOne(targetEntity = JuridicPerson.class)
	@JoinColumn(name = "company_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "company_fk"))
	private JuridicPerson company;
	

	
	

}
