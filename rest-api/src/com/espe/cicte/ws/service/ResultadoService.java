package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.ResultadoDB;
import com.espe.cicte.ws.dto.ResultadoRequest;
import com.espe.cicte.ws.dto.ResultadoResponse;

@Path("/cicte/resultado")
public class ResultadoService {

	private static ResultadoDB ResultadoDB = new ResultadoDB();

	@POST
	@Path("/addResultado")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultadoResponse addResultado(ResultadoRequest request) {
		ResultadoResponse response = new ResultadoResponse();

		response = ResultadoDB.addResultado(request);
		
		return response;
	}
	
	@POST
	@Path("/getResultados")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultadoResponse getResultados(ResultadoRequest request) {
		ResultadoResponse response = new ResultadoResponse();

		//response = ResultadoDB.getResultado();
	
		return response;
	}
	
	@POST
	@Path("/getResultadoMatriz")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultadoResponse getResultadoMatriz(ResultadoRequest request) {
		ResultadoResponse response = new ResultadoResponse();

		//response = ResultadoDB.getResultadoMatriz(request);
	
		return response;
	}
	
	
	@POST
	@Path("/updateResultado")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultadoResponse updateResultado(ResultadoRequest request) {
		ResultadoResponse response = new ResultadoResponse();

		//response = ResultadoDB.updateResultado(request);

		return response;
	}
	
	@POST
	@Path("/deleteResultado")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultadoResponse deleteResultado(ResultadoRequest request) {
		ResultadoResponse response = new ResultadoResponse();

		//response = ResultadoDB.deleteResultado(request);

		return response;
	}
}
