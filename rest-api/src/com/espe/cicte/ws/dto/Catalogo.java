package com.espe.cicte.ws.dto;


public class Catalogo{
	private String tabla;
	private String valor;
	private String detalle;
	
	public String getTabla() {
		return tabla;
	}
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
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
		return "Catalogo [tabla=" + tabla + ", valor=" + valor + ", detalle=" + detalle + ", catalogo="
				 + "]";
	}
	
	
	
	
}
