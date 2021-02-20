package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.IlusionDB;
import com.espe.cicte.ws.db.PlanDB;
import com.espe.cicte.ws.dto.PlanRequest;
import com.espe.cicte.ws.dto.PlanResponse;

@Path("/cicte/plan")
public class PlanService {

	private static PlanDB PlanDB = new PlanDB();
	
	@POST
	@Path("/addPlan")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PlanResponse addPlan(PlanRequest request) {
		PlanResponse response = new PlanResponse();

		response = PlanDB.addPlan(request);
		
		return response;
	}
	
	@POST
	@Path("/getPlan")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PlanResponse getPlan(PlanRequest request) {
		PlanResponse response = new PlanResponse();

		response = PlanDB.getPlan();
	
		return response;
	}
	
	@POST
	@Path("/getPlanByName")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PlanResponse getPlanName(PlanRequest request) {
		PlanResponse response = new PlanResponse();

		response = PlanDB.getPlanByName(request);
	
		return response;
	}
	
	@POST
	@Path("/getPlanById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PlanResponse getPlanById(PlanRequest request) {
		PlanResponse response = new PlanResponse();

		response = PlanDB.getPlanById(request);
	
		return response;
	}
	
	@POST
	@Path("/updatePlan")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PlanResponse updatePlan(PlanRequest request) {
		PlanResponse response = new PlanResponse();

		response = PlanDB.updatePlan(request);

		return response;
	}
	
	@POST
	@Path("/deletePlan")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public PlanResponse deletePlan(PlanRequest request) {
		PlanResponse response = new PlanResponse();

		response = PlanDB.deletePlan(request);

		return response;
	}
}
