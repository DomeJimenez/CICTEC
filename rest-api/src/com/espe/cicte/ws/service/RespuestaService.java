package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.RespuestaDB;
import com.espe.cicte.ws.dto.Respuesta;
import com.espe.cicte.ws.dto.RespuestaRequest;
import com.espe.cicte.ws.dto.RespuestaResponse;

@Path("/cicte/respuesta")
public class RespuestaService {

	private static RespuestaDB RespuestaDB = new RespuestaDB();

	@POST
	@Path("/addRespuesta")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public RespuestaResponse addRespuesta(Respuesta request) {
		RespuestaResponse response = new RespuestaResponse();

		response = RespuestaDB.addRespuesta(request);
		
		return response;
	}
	
	@POST
	@Path("/getRespuesta")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public RespuestaResponse getRespuesta(RespuestaRequest request) {
		RespuestaResponse response = new RespuestaResponse();

		response = RespuestaDB.getRespuesta(request);
	
		return response;
	}
	

	
	@POST
	@Path("/getRespuestaById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public RespuestaResponse getRespuestaById(RespuestaRequest request) {
		RespuestaResponse response = new RespuestaResponse();

		response = RespuestaDB.getRespuestaById(request);
	
		return response;
	}
	
	@POST
	@Path("/updateRespuesta")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public RespuestaResponse updateRespuesta(Respuesta request) {
		RespuestaResponse response = new RespuestaResponse();

		response = RespuestaDB.updateRespuesta(request);

		return response;
	}
	
	@POST
	@Path("/deleteRespuesta")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public RespuestaResponse deleteRespuesta(RespuestaRequest request) {
		RespuestaResponse response = new RespuestaResponse();

		response = RespuestaDB.deleteRespuesta(request);

		return response;
	}
}
