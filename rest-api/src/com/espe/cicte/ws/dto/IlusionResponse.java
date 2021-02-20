package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class IlusionResponse extends BaseResponse{
	private Ilusion[] ilusion;

	public Ilusion[] getIlusion() {
		return ilusion;
	}

	public void setIlusion(Ilusion[] ilusion) {
		this.ilusion = ilusion;
	}

	@Override
	public String toString() {
		return "IlusionResponse [ilusion=" + Arrays.toString(ilusion) + "]";
	}
	
	
}
