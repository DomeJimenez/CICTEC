package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class Plan {
	private int codigo;
	private String nombre;
	private String lugar;
	private String ilusion;
	private String simulador;
	private Byte[] graficoXY;
	private Byte[] graficoXZ;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
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
	public String getIlusion() {
		return ilusion;
	}
	public void setIlusion(String ilusion) {
		this.ilusion = ilusion;
	}
	public Byte[] getGraficoXY() {
		return graficoXY;
	}
	public void setGraficoXY(Byte[] graficoXY) {
		this.graficoXY = graficoXY;
	}
	public Byte[] getGraficoXZ() {
		return graficoXZ;
	}
	public void setGraficoXZ(Byte[] graficoXZ) {
		this.graficoXZ = graficoXZ;
	}
	public String getSimulador() {
		return simulador;
	}
	public void setSimulador(String simulador) {
		this.simulador = simulador;
	}
	@Override
	public String toString() {
		return "Plan [codigo=" + codigo + ", nombre=" + nombre + ", lugar=" + lugar + ", ilusion=" + ilusion
				+ ", simulador=" + simulador + ", graficoXY=" + Arrays.toString(graficoXY) + ", graficoXZ="
				+ Arrays.toString(graficoXZ) + "]";
	}
	
}
