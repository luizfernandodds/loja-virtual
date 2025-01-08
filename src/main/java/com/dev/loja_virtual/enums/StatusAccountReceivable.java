package com.dev.loja_virtual.enums;

import lombok.Getter;

@Getter
public enum StatusAccountReceivable {

	COBRANCA("Pagar"),
	VENCIDA("Vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada");
	
	private String description;
	
	StatusAccountReceivable(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}
