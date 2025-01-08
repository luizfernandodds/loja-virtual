package com.dev.loja_virtual.enums;

import lombok.Getter;

@Getter
public enum AddressType {
	
	COBRANCA("Cobrança"),
	ENTREGA("Entrega");
	
	
	private String descrition;

	AddressType(String descrition) {
		this.descrition = descrition;
	}

	@Override
	public String toString() {
		return this.descrition;
	}
}
