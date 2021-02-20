package com.espe.cicte.ws.dto;

public class SimuladorResponse extends BaseResponse{
	private Simulador[] simulador;

	public Simulador[] getSimulador() {
		return simulador;
	}

	public void setSimulador(Simulador[] simulador) {
		this.simulador = simulador;
	}

	@Override
	public String toString() {
		return "SimuladorResponse [simulador=" + simulador + "]";
	}
	
	

}
