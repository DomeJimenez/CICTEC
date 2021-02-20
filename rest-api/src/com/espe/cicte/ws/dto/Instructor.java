package com.espe.cicte.ws.dto;

public class Instructor {
	
	private int codigo;
	private String id_mil;
	private String cedula;
	private int grado;
	private String nombre;
	private String apellido;
	private String rango;
	private String email;
	
	public String getId_mil() {
		return id_mil;
	}
	public void setId_mil(String id_mil) {
		this.id_mil = id_mil;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public int getGrado() {
		return grado;
	}
	public void setGrado(int grado) {
		this.grado = grado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Instructor [codigo=" + codigo + ", id_mil=" + id_mil + ", cedula=" + cedula + ", grado=" + grado
				+ ", nombre=" + nombre + ", apellido=" + apellido + ", rango=" + rango + ", email=" + email + "]";
	}
	
}
