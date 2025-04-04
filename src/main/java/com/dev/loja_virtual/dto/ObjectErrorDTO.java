package com.dev.loja_virtual.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectErrorDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String error;
	private String code;
	
	
	
}
