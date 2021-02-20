package com.espe.cicte.ws.dto;

public class LoginResponse extends BaseResponse {

	private Login login;
	private boolean auth;
	private boolean access;
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	public boolean isAccess() {
		return access;
	}
	public void setAccess(boolean access) {
		this.access = access;
	}
	@Override
	public String toString() {
		return "LoginResponse [login=" + login + ", auth=" + auth + ", access=" + access + "]";
	}
	
	
	
	
	
	

}
