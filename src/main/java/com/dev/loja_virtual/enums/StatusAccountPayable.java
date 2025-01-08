package com.dev.loja_virtual.enums;

import lombok.Getter;

@Getter
public enum StatusAccountPayable {
	
	COBRANCA("Pagar"),
	VENCIDA("Vencida"),
	ABERTA("Quitada"),
	ALUGUEL("Aluguel"),
	FUNCIONARIO("Funcionario"),
	NEGOCIADA("Renegociada");
	
	
	private String description;
	
	StatusAccountPayable(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
