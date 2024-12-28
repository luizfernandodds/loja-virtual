package com.dev.loja_virtual.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class ProductCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	

}
