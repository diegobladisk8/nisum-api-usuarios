package com.nisum.api.ejercicio.util;

import lombok.Data;

@Data
public class OperationResponse {

	private String mensaje;
	private String status;

	public OperationResponse(String mensaje) {
		this.mensaje = mensaje;

	}

}
