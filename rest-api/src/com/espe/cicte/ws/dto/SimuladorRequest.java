package com.espe.cicte.ws.dto;

public class SimuladorRequest extends BaseRequest{
	private String nombre;
	private String ubicacion;
	private String codigo;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String login) {
		this.nombre = login;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return "SimuladorResponse [nombre=" + nombre + ", ubicacion=" + ubicacion + ", codigo=" + codigo + "]";
	}
}
