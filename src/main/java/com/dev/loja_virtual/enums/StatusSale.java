package com.dev.loja_virtual.enums;

import lombok.Getter;

@Getter
public enum StatusSale {
	
	FINALIZADA("Finalizada"),
	CANCELADA("Cancelada"),
	ABANDONOU_CARRINHO("Abandonou Carrinho");
	
	private String description;
	
	StatusSale(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return this.description;
	}

}
