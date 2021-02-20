package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.PreguntaDB;
import com.espe.cicte.ws.dto.Pregunta;
import com.espe.cicte.ws.dto.PreguntaRequest;
import com.espe.cicte.ws.dto.PreguntaResponse;

@Path("/cicte/pregunta")
public class PreguntaService {
	
	private static PreguntaDB PreguntaDB = new PreguntaDB();

	@POST
	@Path("/addPregunta")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PreguntaResponse addPregunta(Pregunta request) {
		PreguntaResponse response = new PreguntaResponse();

		response = PreguntaDB.addPregunta(request);
		
		return response;
	}
	
	@POST
	@Path("/getPregunta")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PreguntaResponse getPregunta(PreguntaRequest request) {
		PreguntaResponse response = new PreguntaResponse();

		response = PreguntaDB.getPregunta(request);
	
		return response;
	}
	
	
	@POST
	@Path("/getPreguntaById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PreguntaResponse getPreguntaById(PreguntaRequest request) {
		PreguntaResponse response = new PreguntaResponse();

		response = PreguntaDB.getPreguntaById(request);
	
		return response;
	}
	
	@POST
	@Path("/updatePregunta")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PreguntaResponse updatePregunta(Pregunta request) {
		PreguntaResponse response = new PreguntaResponse();

		response = PreguntaDB.updatePregunta(request);

		return response;
	}
	
	@POST
	@Path("/deletePregunta")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PreguntaResponse deletePregunta(PreguntaRequest request) {
		PreguntaResponse response = new PreguntaResponse();

		response = PreguntaDB.deletePregunta(request);

		return response;
	}

}
