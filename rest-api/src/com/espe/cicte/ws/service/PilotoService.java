package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.PilotoDB;
import com.espe.cicte.ws.dto.Piloto;
import com.espe.cicte.ws.dto.PilotoRequest;
import com.espe.cicte.ws.dto.PilotoResponse;

@Path("/cicte/pilotos")
public class PilotoService {
	
	private static PilotoDB pilotoDB = new PilotoDB();

	@POST
	@Path("/addPiloto")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PilotoResponse addPiloto(Piloto request) {
		PilotoResponse response = new PilotoResponse();

		response = pilotoDB.addPiloto(request);
		
		return response;
	}
	
	@POST
	@Path("/getPilotos")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PilotoResponse getPilotos() {
		PilotoResponse response = new PilotoResponse();

		response = pilotoDB.getPilotos();
	
		return response;
	}
	
	@POST
	@Path("/getPilotoById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PilotoResponse getPilotoById(PilotoRequest request) {
		PilotoResponse response = new PilotoResponse();

		response = pilotoDB.getPilotoById(request);
	
		return response;
	}
	
	@POST
	@Path("/getFotoPiloto")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PilotoResponse getFotoPiloto(PilotoRequest request) {
		PilotoResponse response = new PilotoResponse();

		response = pilotoDB.getFotoPiloto(request);

		return response;
	}
	
	@POST
	@Path("/updatePiloto")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PilotoResponse updatePiloto(Piloto request) {
		PilotoResponse response = new PilotoResponse();

		response = pilotoDB.updatePiloto(request);

		return response;
	}
	
	@POST
	@Path("/deletePiloto")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PilotoResponse deletePiloto(Piloto request) {
		PilotoResponse response = new PilotoResponse();

		response = pilotoDB.deletePiloto(request);

		return response;
	}
	
}
