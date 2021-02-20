package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class Formulario {
	private int id;
	private String nombre;
	private String fecha_crea;
	private String fecha_mod;
	private String detalle;
	private String codigo;
	private String estado; 
	private Pregunta pregunta;
	private Pregunta[] preguntaList;
	
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
	public String getFecha_crea() {
		return fecha_crea;
	}
	public void setFecha_crea(String fecha_crea) {
		this.fecha_crea = fecha_crea;
	}
	public String getFecha_mod() {
		return fecha_mod;
	}
	public void setFecha_mod(String fecha_mod) {
		this.fecha_mod = fecha_mod;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pregunta[] getPreguntaList() {
		return preguntaList;
	}
	public void setPreguntaList(Pregunta[] preguntaList) {
		this.preguntaList = preguntaList;
	}
	@Override
	public String toString() {
		return "Formulario [id=" + id + ", nombre=" + nombre + ", fecha_crea=" + fecha_crea + ", fecha_mod=" + fecha_mod
				+ ", detalle=" + detalle + ", codigo=" + codigo + ", estado=" + estado + ", pregunta=" + pregunta
				+ ", preguntaList=" + Arrays.toString(preguntaList) + "]";
	}	
}
