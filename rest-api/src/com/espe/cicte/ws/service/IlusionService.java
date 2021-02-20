package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.IlusionDB;
import com.espe.cicte.ws.dto.IlusionRequest;
import com.espe.cicte.ws.dto.IlusionResponse;


@Path("/cicte/ilusion")
public class IlusionService {

	private static IlusionDB IlusionDB = new IlusionDB();

	@POST
	@Path("/addIlusion")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public IlusionResponse addIlusion(IlusionRequest request) {
		IlusionResponse response = new IlusionResponse();

		response = IlusionDB.addIlusion(request);
		
		return response;
	}
	
	@POST
	@Path("/getIlusion")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public IlusionResponse getIlusion(IlusionRequest request) {
		IlusionResponse response = new IlusionResponse();

		response = IlusionDB.getIlusion();
	
		return response;
	}
	
	@POST
	@Path("/getIlusionByName")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public IlusionResponse getIlusionName(IlusionRequest request) {
		IlusionResponse response = new IlusionResponse();

		response = IlusionDB.getIlusionByName(request);
	
		return response;
	}
	
	@POST
	@Path("/getIlusionById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public IlusionResponse getIlusionById(IlusionRequest request) {
		IlusionResponse response = new IlusionResponse();

		response = IlusionDB.getIlusionById(request);
	
		return response;
	}
	
	@POST
	@Path("/updateIlusion")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public IlusionResponse updateIlusion(IlusionRequest request) {
		IlusionResponse response = new IlusionResponse();

		response = IlusionDB.updateIlusion(request);

		return response;
	}
	
	@POST
	@Path("/deleteIlusion")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public IlusionResponse deleteIlusion(IlusionRequest request) {
		IlusionResponse response = new IlusionResponse();

		response = IlusionDB.deleteIlusion(request);

		return response;
	}
}
