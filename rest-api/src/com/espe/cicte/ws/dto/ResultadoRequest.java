package com.espe.cicte.ws.dto;

public class ResultadoRequest  extends BaseRequest{

	public String instructor;
	public String piloto;
	public int evaluacion;
	public int formulario;
	public int pregunta;
	public int resultado;
	public int respuesta;
	
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getPiloto() {
		return piloto;
	}
	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}
	public int getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(int evaluacion) {
		this.evaluacion = evaluacion;
	}
	public int getFormulario() {
		return formulario;
	}
	public void setFormulario(int formulario) {
		this.formulario = formulario;
	}
	public int getPregunta() {
		return pregunta;
	}
	public void setPregunta(int pregunta) {
		this.pregunta = pregunta;
	}
	public int getResultado() {
		return resultado;
	}
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	public int getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
	@Override
	public String toString() {
		return "ResultadoRequest [instructor=" + instructor + ", piloto=" + piloto + ", evaluacion=" + evaluacion
				+ ", formulario=" + formulario + ", pregunta=" + pregunta + ", resultado=" + resultado + ", respuesta="
				+ respuesta + "]";
	}
	
}
