package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.EvaluacionDB;
import com.espe.cicte.ws.dto.EvaluacionRequest;
import com.espe.cicte.ws.dto.EvaluacionResponse;

@Path("/cicte/evaluacion")
public class EvaluacionService {

	private static EvaluacionDB EvaluacionDB = new EvaluacionDB();

	@POST
	@Path("/addEvaluacion")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public EvaluacionResponse addEvaluacion(EvaluacionRequest request) {
		EvaluacionResponse response = new EvaluacionResponse();

		response = EvaluacionDB.addEvaluacion(request);
		
		return response;
	}
	
	@POST
	@Path("/getEvaluacion")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public EvaluacionResponse getEvaluacion(EvaluacionRequest request) {
		EvaluacionResponse response = new EvaluacionResponse();

		response = EvaluacionDB.getEvaluacion();
	
		return response;
	}
	
	/*@POST
	@Path("/getEvaluacionByName")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public EvaluacionResponse getEvaluacionName(EvaluacionRequest request) {
		EvaluacionResponse response = new EvaluacionResponse();

		response = EvaluacionDB.getEvaluacionByName(request);
	
		return response;
	}
	
	@POST
	@Path("/getEvaluacionById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public EvaluacionResponse getEvaluacionById(EvaluacionRequest request) {
		EvaluacionResponse response = new EvaluacionResponse();

		response = EvaluacionDB.getEvaluacionById(request);
	
		return response;
	}*/
	
	@POST
	@Path("/updateEvaluacion")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public EvaluacionResponse updateEvaluacion(EvaluacionRequest request) {
		EvaluacionResponse response = new EvaluacionResponse();

		response = EvaluacionDB.updateEvaluacion(request);

		return response;
	}
	
	@POST
	@Path("/deleteEvaluacion")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public EvaluacionResponse deleteEvaluacion(EvaluacionRequest request) {
		EvaluacionResponse response = new EvaluacionResponse();

		response = EvaluacionDB.deleteEvaluacion(request);

		return response;
	}
}
