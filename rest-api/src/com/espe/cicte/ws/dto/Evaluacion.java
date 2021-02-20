package com.espe.cicte.ws.dto;

import java.sql.Blob;

public class Evaluacion {
	
	private int codigo;
	private String fecha;
	private String observacion;
	private Piloto piloto;
	private Instructor instructor;
	private Plan plan;
	private Blob xy;
	private Blob xz;
	private int funcion;
	private int aeronave;
	private int horasVuelo;
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Piloto getPiloto() {
		return piloto;
	}
	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Blob getXy() {
		return xy;
	}
	public void setXy(Blob xy) {
		this.xy = xy;
	}
	public Blob getXz() {
		return xz;
	}
	public void setXz(Blob xz) {
		this.xz = xz;
	}
	public int getFuncion() {
		return funcion;
	}
	public void setFuncion(int funcion) {
		this.funcion = funcion;
	}
	public int getAeronave() {
		return aeronave;
	}
	public void setAeronave(int aeronave) {
		this.aeronave = aeronave;
	}
	public int getHorasVuelo() {
		return horasVuelo;
	}
	public void setHorasVuelo(int horasVuelo) {
		this.horasVuelo = horasVuelo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return "Evaluacion [codigo=" + codigo + ", fecha=" + fecha + ", observacion=" + observacion + ", piloto="
				+ piloto + ", instructor=" + instructor + ", plan=" + plan + ", xy=" + xy + ", xz=" + xz + ", funcion="
				+ funcion + ", aeronave=" + aeronave + ", horasVuelo=" + horasVuelo + "]";
	}
	
	
	
}
