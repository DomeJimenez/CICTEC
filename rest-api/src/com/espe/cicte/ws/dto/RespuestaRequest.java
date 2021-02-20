package com.espe.cicte.ws.dto;

public class RespuestaRequest extends BaseRequest {

	private int codigo;
	private int tipo;
	private String detalle;
	private String estado;
	private Pregunta pregunta;
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return "RespuestaRequest [codigo=" + codigo + ", tipo=" + tipo + ", detalle=" + detalle + ", estado=" + estado
				+ ", pregunta=" + pregunta + "]";
	}
		
}
