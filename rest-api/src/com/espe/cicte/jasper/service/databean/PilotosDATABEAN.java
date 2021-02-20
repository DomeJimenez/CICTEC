package com.espe.cicte.jasper.service.databean;

import java.util.List;

import com.espe.cicte.ws.dto.Piloto;

public class PilotosDATABEAN {

	private List<Piloto> pilotos;

	public List<Piloto> getPilotos() {
		return pilotos;
	}

	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	@Override
	public String toString() {
		return "PilotosDATABEAN [pilotos=" + pilotos + "]";
	}
	
	
}
