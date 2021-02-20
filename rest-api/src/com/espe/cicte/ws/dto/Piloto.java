package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class Piloto {
	private int codigo;
	private String nombres;
	private String apellidos;
	private String email;
	private String fecha_nac;
	private int grado;
	private String fuerza;
	private String fecha_reg;
	private String fecha_mod;
	private String telefono;
	private String nacionalidad;
	private String id;
	private String id_mil;
	private byte[] foto;
	private String estado;
	private String unidad;
	private String rango;
	private String servicio;
	private int hv;
	
	
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFecha_nac() {
		return fecha_nac;
	}
	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}
	public int getGrado() {
		return grado;
	}
	public void setGrado(int grado) {
		this.grado = grado;
	}
	public String getFuerza() {
		return fuerza;
	}
	public void setFuerza(String fuerza) {
		this.fuerza = fuerza;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_mil() {
		return id_mil;
	}
	public void setId_mil(String id_mil) {
		this.id_mil = id_mil;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getFecha_reg() {
		return fecha_reg;
	}
	public void setFecha_reg(String fecha_reg) {
		this.fecha_reg = fecha_reg;
	}
	public String getFecha_mod() {
		return fecha_mod;
	}
	public void setFecha_mod(String fecha_mod) {
		this.fecha_mod = fecha_mod;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getHv() {
		return hv;
	}
	public void setHv(int hv) {
		this.hv = hv;
	}
	@Override
	public String toString() {
		return "Piloto [codigo=" + codigo + ", nombres=" + nombres + ", apellidos=" + apellidos + ", email=" + email
				+ ", fecha_nac=" + fecha_nac + ", grado=" + grado + ", fuerza=" + fuerza + ", fecha_reg=" + fecha_reg
				+ ", fecha_mod=" + fecha_mod + ", telefono=" + telefono + ", nacionalidad=" + nacionalidad + ", id="
				+ id + ", id_mil=" + id_mil + ", foto=" + Arrays.toString(foto) + ", estado=" + estado + ", unidad="
				+ unidad + ", rango=" + rango + ", servicio=" + servicio + ", hv=" + hv + "]";
	}
	
}
