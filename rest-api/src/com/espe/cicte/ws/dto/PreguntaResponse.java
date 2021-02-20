package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class PreguntaResponse extends BaseResponse{
	private Pregunta pregunta[];

	public Pregunta[] getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta[] pregunta) {
		this.pregunta = pregunta;
	}

	@Override
	public String toString() {
		return "PreguntaResponse [pregunta=" + Arrays.toString(pregunta) + "]";
	}
	
	
}
