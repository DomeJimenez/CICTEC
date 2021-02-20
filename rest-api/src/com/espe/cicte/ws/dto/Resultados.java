package com.espe.cicte.ws.dto;

public class Resultados {
	
	private Evaluacion evaluacion;
	private Pregunta pregunta;
	private Respuesta respuesta;
	private Formulario formulario;
	
	public Evaluacion getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public Respuesta getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	public Formulario getFormulario() {
		return formulario;
	}
	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}
	@Override
	public String toString() {
		return "Resultados [evaluacion=" + evaluacion + ", pregunta=" + pregunta + ", respuesta=" + respuesta
				+ ", formulario=" + formulario + "]";
	}
}
