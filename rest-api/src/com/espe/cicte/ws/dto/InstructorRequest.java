package com.espe.cicte.ws.dto;

public class InstructorRequest extends BaseRequest{
	
	private int codigo;
	private String email;
	private String nombre;
	private String apellido;
	private String id_mil;
	private String cedula;
	private int grado;
	private String fechaMod;
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
	public String getFechaMod() {
		return fechaMod;
	}
	public void setFechaMod(String fechaMod) {
		this.fechaMod = fechaMod;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "InstructorRequest [codigo=" + codigo + ", email=" + email + ", nombre=" + nombre + ", apellido="
				+ apellido + ", id_mil=" + id_mil + ", cedula=" + cedula + ", grado=" + grado + ", fechaMod=" + fechaMod
				+ "]";
	}
}
