package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class RespuestaResponse extends BaseResponse{

	private Respuesta[] respuesta;

	public Respuesta[] getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta[] respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public String toString() {
		return "RespuestaResponse [respuesta=" + Arrays.toString(respuesta) + "]";
	}
	
	
}
