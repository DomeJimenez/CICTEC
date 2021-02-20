package com.espe.cicte.ws.dto;

public class Login {

	private LoginRequest login;
	private int codigo;
	private String usuario;
	private String instructor;
	private int intento;
	private String rol;
	private String ultimoIngreso;

	public LoginRequest getLogin() {
		return login;
	}

	public void setLogin(LoginRequest login) {
		this.login = login;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getIntento() {
		return intento;
	}

	public void setIntento(int intento) {
		this.intento = intento;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getUltimoIngreso() {
		return ultimoIngreso;
	}

	public void setUltimoIngreso(String ultimoIngreso) {
		this.ultimoIngreso = ultimoIngreso;
	}

	@Override
	public String toString() {
		return "Login [login=" + login + ", codigo=" + codigo + ", usuario=" + usuario + ", instructor=" + instructor
				+ ", intento=" + intento + ", rol=" + rol + ", ultimoIngreso=" + ultimoIngreso + "]";
	}
	
	
}
