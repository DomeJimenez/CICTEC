package com.espe.cicte.ws.dto;

public class PasswordResponse extends BaseResponse {

	private String changeLogin;
	private int idEnte;
	private String originalLogin;
	
	public String getChangeLogin() {
		return changeLogin;
	}

	public void setChangeLogin(String changeLogin) {
		this.changeLogin = changeLogin;
	}

	public int getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(int idEnte) {
		this.idEnte = idEnte;
	}

	public String getOriginalLogin() {
		return originalLogin;
	}

	public void setOriginalLogin(String originalLogin) {
		this.originalLogin = originalLogin;
	}
	
}
