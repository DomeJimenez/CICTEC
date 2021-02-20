package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class EvaluacionResponse extends BaseResponse{

	public Evaluacion[] evaluacion;

	public Evaluacion[] getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion[] evaluacion) {
		this.evaluacion = evaluacion;
	}

	@Override
	public String toString() {
		return "EvaluacionResponse [evaluacion=" + Arrays.toString(evaluacion) + "]";
	}

	
}
