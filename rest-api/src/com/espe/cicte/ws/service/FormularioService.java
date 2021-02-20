package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.FormularioDB;
import com.espe.cicte.ws.dto.FormularioRequest;
import com.espe.cicte.ws.dto.FormularioResponse;

@Path("/cicte/formulario")
public class FormularioService {
	
	private static FormularioDB FormularioDB = new FormularioDB();

	@POST
	@Path("/addFormulario")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public FormularioResponse addFormulario(FormularioRequest request) {
		FormularioResponse response = new FormularioResponse();

		response = FormularioDB.addFormulario(request);
		
		return response;
	}
	
	@POST
	@Path("/getFormularios")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public FormularioResponse getFormularios(FormularioRequest request) {
		FormularioResponse response = new FormularioResponse();

		response = FormularioDB.getFormulario();
	
		return response;
	}
	
	@POST
	@Path("/getFormularioMatriz")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public FormularioResponse getFormularioMatriz(FormularioRequest request) {
		FormularioResponse response = new FormularioResponse();

		response = FormularioDB.getFormularioMatriz(request);
	
		return response;
	}
	
	
	@POST
	@Path("/updateFormulario")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public FormularioResponse updateFormulario(FormularioRequest request) {
		FormularioResponse response = new FormularioResponse();

		response = FormularioDB.updateFormulario(request);

		return response;
	}
	
	@POST
	@Path("/deleteFormulario")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public FormularioResponse deleteFormulario(FormularioRequest request) {
		FormularioResponse response = new FormularioResponse();

		response = FormularioDB.deleteFormulario(request);

		return response;
	}
}
