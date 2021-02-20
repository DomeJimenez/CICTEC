package com.espe.cicte.ws.dto;

public class Tabla {
	
	private String valor;
	private String detalle;
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	@Override
	public String toString() {
		return "Tabla [valor=" + valor + ", detalle=" + detalle + "]";
	}
	
	
				
}
