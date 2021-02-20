package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.LoginDB;
import com.espe.cicte.ws.dto.LoginRequest;
import com.espe.cicte.ws.dto.LoginResponse;


@Path("/cicte/security")
public class LoginService {
	
	private static LoginDB LoginDB = new LoginDB();
	
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public LoginResponse login(LoginRequest request) {
		LoginResponse response = new LoginResponse();
		response = LoginDB.login(request);

		return response;
	}
	
	@POST
	@Path("/addUser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public LoginResponse addUser(LoginRequest request) {
		LoginResponse response = new LoginResponse();
		response = LoginDB.addUser(request);

		return response;
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/sayHello")
	public String sayHello() {
		return "<h1>Hello World</h1>";
	}
}
