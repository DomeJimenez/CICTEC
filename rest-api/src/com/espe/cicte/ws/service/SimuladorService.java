package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.SimuladorDB;
import com.espe.cicte.ws.dto.SimuladorRequest;
import com.espe.cicte.ws.dto.SimuladorResponse;

@Path("/cicte/simulador")
public class SimuladorService {

	private static SimuladorDB SimuladorDB = new SimuladorDB();

	@POST
	@Path("/addSimulador")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public SimuladorResponse addSimulador(SimuladorRequest request) {
		SimuladorResponse response = new SimuladorResponse();

		response = SimuladorDB.addSimulador(request);
		
		return response;
	}
	
	@POST
	@Path("/getSimulador")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public SimuladorResponse getSimulador(SimuladorRequest request) {
		SimuladorResponse response = new SimuladorResponse();

		response = SimuladorDB.getSimulador();
	
		return response;
	}
	
	@POST
	@Path("/getSimuladorById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public SimuladorResponse getSimuladorMatriz(SimuladorRequest request) {
		SimuladorResponse response = new SimuladorResponse();

		response = SimuladorDB.getSimuladorByCode(request);
	
		return response;
	}
	
	
	@POST
	@Path("/updateSimulador")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public SimuladorResponse updateSimulador(SimuladorRequest request) {
		SimuladorResponse response = new SimuladorResponse();

		response = SimuladorDB.updateSimulador(request);

		return response;
	}
	
	@POST
	@Path("/deleteSimulador")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public SimuladorResponse deleteSimulador(SimuladorRequest request) {
		SimuladorResponse response = new SimuladorResponse();

		response = SimuladorDB.deleteSimulador(request);

		return response;
	}
}
