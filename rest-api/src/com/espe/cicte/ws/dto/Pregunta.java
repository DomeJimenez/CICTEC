package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class Pregunta {
	private int codigo;
	private int codigoFormulario;
	private String criterio;
	private String estado;
	private String formularioNombre;
	private Formulario formulario;
	private Respuesta respuesta;
	private Respuesta[] respuestaList;
	
	public String getCriterio() {
		return criterio;
	}
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Formulario getFormulario() {
		return formulario;
	}
	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}
	public Respuesta getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int id) {
		this.codigo = id;
	}
	public Respuesta[] getRespuestaList() {
		return respuestaList;
	}
	public void setRespuestaList(Respuesta[] respuestaList) {
		this.respuestaList = respuestaList;
	}
	public String getFormularioNombre() {
		return formularioNombre;
	}
	public void setFormularioNombre(String formularioNombre) {
		this.formularioNombre = formularioNombre;
	}
	public int getCodigoFormulario() {
		return codigoFormulario;
	}
	public void setCodigoFormulario(int codigoFormulario) {
		this.codigoFormulario = codigoFormulario;
	}
	@Override
	public String toString() {
		return "Pregunta [codigo=" + codigo + ", codigoFormulario=" + codigoFormulario + ", criterio=" + criterio
				+ ", estado=" + estado + ", formularioNombre=" + formularioNombre + ", formulario=" + formulario
				+ ", respuesta=" + respuesta + ", respuestaList=" + Arrays.toString(respuestaList) + "]";
	}
}
