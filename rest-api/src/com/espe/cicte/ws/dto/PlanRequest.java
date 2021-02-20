package com.espe.cicte.ws.dto;

import java.sql.Blob;

public class PlanRequest extends BaseRequest{
	private int codigo;
	private String nombre;
	private String lugar;
	private Ilusion ilusion;
	private Blob graficoXY;
	private Blob graficoXZ;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Ilusion getIlusion() {
		return ilusion;
	}
	public void setIlusion(Ilusion ilusion) {
		this.ilusion = ilusion;
	}
	public Blob getGraficoXY() {
		return graficoXY;
	}
	public void setGraficoXY(Blob graficoXY) {
		this.graficoXY = graficoXY;
	}
	public Blob getGraficoXZ() {
		return graficoXZ;
	}
	public void setGraficoXZ(Blob graficoXZ) {
		this.graficoXZ = graficoXZ;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return "PlanRequest [codigo=" + codigo + ", nombre=" + nombre + ", lugar=" + lugar + ", ilusion=" + ilusion
				+ ", graficoXY=" + graficoXY + ", graficoXZ=" + graficoXZ + "]";
	}
}
