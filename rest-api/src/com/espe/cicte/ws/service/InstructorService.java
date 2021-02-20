package com.espe.cicte.ws.service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.jasper.service.PilotoReport;
import com.espe.cicte.ws.db.InstructorDB;
import com.espe.cicte.ws.db.LoginDB;
import com.espe.cicte.ws.dto.InstructorRequest;
import com.espe.cicte.ws.dto.InstructorResponse;
import com.espe.cicte.ws.dto.LoginRequest;
import com.espe.cicte.ws.utils.Email;
import com.espe.cicte.ws.utils.Utils;

import net.sf.jasperreports.engine.JRException;

@Path("/cicte/instructor")
public class InstructorService {
	
	private static InstructorDB InstructorDB = new InstructorDB();
	private static LoginDB LoginDB = new LoginDB();

	@POST
	@Path("/addInstructor")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public InstructorResponse addInstructor(InstructorRequest request) {
		InstructorResponse response = new InstructorResponse();
		PilotoReport report = new PilotoReport();
		Email email = new Email();
		response = InstructorDB.addInstructor(request);
		String claveTemp = Utils.getInstance().randomString(6);
		
		LoginRequest login = new LoginRequest();
		login.setEmail(request.getEmail());
		login.setUsername(request.getEmail());
		login.setPassword(claveTemp);
		login.setInstructor(request.getId_mil());
		
		if(LoginDB.addUser(login).getSuccess()) {
			email.generaClave( request.getEmail(), claveTemp);
		}
		
		try {
			report.reportePilotos();
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@POST
	@Path("/getInstructor")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public InstructorResponse getInstructor() {
		InstructorResponse response = new InstructorResponse();

		response = InstructorDB.getInstructor();
	
		return response;
	}
	
	@POST
	@Path("/getInstructorById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public InstructorResponse getInstructorById(InstructorRequest request) {
		InstructorResponse response = new InstructorResponse();

		response = InstructorDB.getInstructorById(request);
	
		return response;
	}
	
	@POST
	@Path("/updateInstructor")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public InstructorResponse updateInstructor(InstructorRequest request) {
		InstructorResponse response = new InstructorResponse();

		response = InstructorDB.updateInstructor(request);

		return response;
	}
	
	@POST
	@Path("/deleteInstructor")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public InstructorResponse deleteInstructor(InstructorRequest request) {
		InstructorResponse response = new InstructorResponse();
		response = InstructorDB.deleteInstructor(request);

		return response;
	}
	

}
