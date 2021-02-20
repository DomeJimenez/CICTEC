package com.espe.cicte.ws.dto;

import java.sql.Blob;
import java.util.Arrays;

public class EvaluacionRequest extends BaseRequest{
	
	private int codigo;
	private String observacion; 
	private String piloto; 
	private String instructor; 
	private int plan; 
	private int funcion; 
	private int aeronave; 
	private int horas_vuelo;
	private byte[] grafico;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getPiloto() {
		return piloto;
	}
	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public int getPlan() {
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
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
	public int getHoras_vuelo() {
		return horas_vuelo;
	}
	public void setHoras_vuelo(int horas_vuelo) {
		this.horas_vuelo = horas_vuelo;
	}
	public byte[] getGrafico() {
		return grafico;
	}
	public void setGrafico(byte[] grafico) {
		this.grafico = grafico;
	}
	@Override
	public String toString() {
		return "EvaluacionRequest [codigo=" + codigo + ", observacion=" + observacion + ", piloto=" + piloto
				+ ", instructor=" + instructor + ", plan=" + plan + ", funcion=" + funcion + ", aeronave=" + aeronave
				+ ", horas_vuelo=" + horas_vuelo + ", grafico=" + Arrays.toString(grafico) + "]";
	}
}
