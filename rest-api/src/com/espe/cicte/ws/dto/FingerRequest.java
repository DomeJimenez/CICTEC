package com.espe.cicte.ws.dto;

public class FingerRequest extends BaseRequest{
	
	private String id;
	private byte[] min1;
	private byte[] min2;
	private byte[] val1;
	private String mano;
	private String dedo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public byte[] getMin1() {
		return min1;
	}
	public void setMin1(byte[] min1) {
		this.min1 = min1;
	}
	public byte[] getMin2() {
		return min2;
	}
	public void setMin2(byte[] min2) {
		this.min2 = min2;
	}
	public byte[] getVal1() {
		return val1;
	}
	public void setVal1(byte[] val1) {
		this.val1 = val1;
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
	
	
}
