package com.espe.cicte.ws.dto;

public class LoginRequest extends BaseRequest {

	private String username;
	private String email;
	private String password;
	private String terminal;
	private String intento;
	private String instructor;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getIntento() {
		return intento;
	}
	public void setIntento(String intento) {
		this.intento = intento;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", email=" + email + ", password=" + password + ", terminal="
				+ terminal + ", intento=" + intento + ", instructor=" + instructor + "]";
	}
	
	
}
