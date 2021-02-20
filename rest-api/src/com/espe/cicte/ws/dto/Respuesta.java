package com.espe.cicte.ws.dto;

public class Respuesta {
	
	private int codigo;
	private Pregunta pregunta;
	private String detalle;
	private int tipo;
	private int preguntaCodigo;
	private String tipoRespuesta;
	private String estado;
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int id) {
		this.codigo = id;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getTipoRespuesta() {
		return tipoRespuesta;
	}
	public void setTipoRespuesta(String tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getPreguntaCodigo() {
		return preguntaCodigo;
	}
	public void setPreguntaCodigo(int preguntaCodigo) {
		this.preguntaCodigo = preguntaCodigo;
	}
	@Override
	public String toString() {
		return "Respuesta [codigo=" + codigo + ", pregunta=" + pregunta + ", detalle=" + detalle + ", tipo=" + tipo
				+ ", preguntaCodigo=" + preguntaCodigo + ", tipoRespuesta=" + tipoRespuesta + ", estado=" + estado
				+ "]";
	}
	
	
}
