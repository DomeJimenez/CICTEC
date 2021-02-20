package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class FingerResponse extends BaseResponse{

	private String mano;
	private String dedo;
	private String id_mil;
	private byte[] regMin1;
	private byte[] regMin2;
	
	public FingerResponse() {
	}
	
	
	public FingerResponse(String mano, String dedo, String id_mil, byte[] regMin1, byte[] regMin2) {
		super();
		this.mano = mano;
		this.dedo = dedo;
		this.id_mil = id_mil;
		this.regMin1 = regMin1;
		this.regMin2 = regMin2;
	}


	public String getMano() {
		return mano;
	}
	public void setMano(String mano) {
		this.mano = mano;
	}
	public String getDedo() {
		return dedo;
	}
	public void setDedo(String dedo) {
		this.dedo = dedo;
	}
	public String getId_mil() {
		return id_mil;
	}
	public void setId_mil(String id_mil) {
		this.id_mil = id_mil;
	}
	public byte[] getRegMin1() {
		return regMin1;
	}
	public void setRegMin1(byte[] regMin1) {
		this.regMin1 = regMin1;
	}
	public byte[] getRegMin2() {
		return regMin2;
	}
	public void setRegMin2(byte[] regMin2) {
		this.regMin2 = regMin2;
	}


	@Override
	public String toString() {
		return "FingerResponse [mano=" + mano + ", dedo=" + dedo + ", id_mil=" + id_mil + ", regMin1="
				+ Arrays.toString(regMin1) + ", regMin2=" + Arrays.toString(regMin2) + "]";
	}	
	
}
