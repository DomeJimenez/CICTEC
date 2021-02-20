package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class PilotoResponse extends BaseResponse{

	private Piloto[]  piloto;

	
	
	public PilotoResponse(Piloto[] piloto) {
		super();
		this.piloto = piloto;
	}
	
	

	public PilotoResponse() {
		super();
	}



	public Piloto[] getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto[] piloto) {
		this.piloto = piloto;
	}

	@Override
	public String toString() {
		return "PilotoResponse [piloto=" + Arrays.toString(piloto) + "]";
	}
	
	
}
