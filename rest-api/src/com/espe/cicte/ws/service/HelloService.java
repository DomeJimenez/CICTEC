package com.espe.cicte.ws.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/HelloWorld")
public class HelloService {
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/sayHello")
	public String sayHello() {
		return "<h1>Hello World</h1>";
	}
}
