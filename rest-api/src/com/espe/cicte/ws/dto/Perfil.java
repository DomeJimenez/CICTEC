package com.espe.cicte.ws.dto;

public class Perfil {
	private String codigo;
	private String nombre;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Perfil [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	
	

}
