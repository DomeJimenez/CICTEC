package com.espe.cicte.ws.dto;

public class Ilusion {
	
	private int codigo;
	private String nombre;
	private String tipo;
	private String simulador;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getSimulador() {
		return simulador;
	}
	public void setSimulador(String simulador) {
		this.simulador = simulador;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return "Ilusion [codigo=" + codigo + ", nombre=" + nombre + ", tipo=" + tipo + ", simulador=" + simulador + "]";
	}
	
	
}
