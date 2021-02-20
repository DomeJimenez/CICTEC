package com.espe.cicte.ws.dto;

public class IlusionRequest extends BaseRequest{
	private String nombre;
	private String tipo;
	private Simulador simulador;
	private int codigo;
	
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
	public Simulador getSimulador() {
		return simulador;
	}
	public void setSimulador(Simulador simulador) {
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
		return "IlusionRequest [nombre=" + nombre + ", tipo=" + tipo + ", simulador=" + simulador + ", codigo=" + codigo
				+ "]";
	}
	
}
