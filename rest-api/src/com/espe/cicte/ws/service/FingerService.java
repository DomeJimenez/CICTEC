package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.BiometricDB;
import com.espe.cicte.ws.dto.BaseResponse;
import com.espe.cicte.ws.dto.FingerRequest;
import com.espe.cicte.ws.dto.FingerResponse;
import com.espe.cicte.ws.dto.Message;
import com.espe.cicte.ws.utils.JSGD;

@Path("/cicte/finger")
public class FingerService {

	private static JSGD huella = new JSGD();

	private static BiometricDB biometricDB = new BiometricDB();

	byte[] regMin1 = new byte[400];
	byte[] regMin2 = new byte[400];
	BaseResponse responseBase = new BaseResponse();
	Message messageResponse = new Message();
	
	@POST
	@Path("/getFinger")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	private FingerResponse getFinger(FingerRequest request) {
		FingerResponse response = new FingerResponse();
		response = biometricDB.getFingerPosition(request);
		return response;
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/sayFinger")
	public String sayHello() {
		return "<h1>Hello in Finger</h1>";
	}

	@POST
	@Path("/validate")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public FingerResponse validaHuella(FingerRequest request) {
		FingerResponse response = new FingerResponse();
		FingerResponse responseFinger = new FingerResponse();
		responseFinger = this.getFingerById(request.getId());
		
		if (responseFinger.getSuccess()) {
			request.setMin1(responseFinger.getRegMin1());
			request.setMin2(responseFinger.getRegMin2());
			response.setDedo(responseFinger.getDedo());
			response.setMano(responseFinger.getMano());
			responseBase = huella.verify(request.getMin1(), request.getMin2(), huella.captureV1());
			if(responseBase.getSuccess()) {
				response.setSuccess(true);				
			}else {
				response.setSuccess(false);
				response.setMessage(responseBase.getMessage());
			}
		}	
		return response;
	}

	@POST
	@Path("/capture")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public FingerResponse capturaHuella(FingerRequest request) {
		FingerResponse response = new FingerResponse();
		Message messageResponse = new Message();
		regMin1 = huella.captureR1();
		regMin2 = huella.captureR2();

		if (huella.register(regMin1, regMin2)) {
			request.setMin1(regMin1);
			request.setMin2(regMin2);
			if (addFingerById(request))
				response.setSuccess(true);
			else {
				messageResponse.setCode("001");
				messageResponse.setMessage("Error en la inserción de datos biométricos");
				response.setMessage(messageResponse);
				response.setSuccess(false);
			}
		} else {
			messageResponse.setCode("002");
			messageResponse.setMessage("Error en captura de las huella");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	private FingerResponse getFingerById(String id) {
		FingerResponse response = new FingerResponse();
		response = biometricDB.getFingerById(id);
		return response;
	}
	
	@POST
	@Path("/update")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public FingerResponse actualizaHuella(FingerRequest request) {
		FingerResponse response = new FingerResponse();
		Message messageResponse = new Message();
		regMin1 = huella.captureR1();
		regMin2 = huella.captureR2();

		if (huella.register(regMin1, regMin2)) {
			request.setMin1(regMin1);
			request.setMin2(regMin2);
			if (updateFingerById(request))
				response.setSuccess(true);
			else {
				messageResponse.setCode("001");
				messageResponse.setMessage("Error en la actualizacion de datos biométricos");
				response.setMessage(messageResponse);
				response.setSuccess(false);
			}
		} else {
			messageResponse.setCode("002");
			messageResponse.setMessage("Error en captura de las huella");
			response.setMessage(messageResponse);
			response.setSuccess(false);
		}
		return response;
	}

	private boolean addFingerById(FingerRequest request) {
		return biometricDB.addFingerById(request);
	}
	
	private boolean updateFingerById(FingerRequest request) {
		return biometricDB.updateFingerById(request);
	}
	
	@POST
	@Path("/getFingerStatus")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public FingerResponse getIlusion(FingerRequest request) {
		FingerResponse response = new FingerResponse();

		response = biometricDB.getFingerPosition(request);
	
		return response;
	}

}
