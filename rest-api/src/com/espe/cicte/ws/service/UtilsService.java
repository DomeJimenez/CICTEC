package com.espe.cicte.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.espe.cicte.ws.db.CatalogoDB;
import com.espe.cicte.ws.dto.Catalogo;
import com.espe.cicte.ws.dto.CatalogoResponse;

@Path("/cicte/utils")
public class UtilsService {

	private static CatalogoDB CatalogoDB = new CatalogoDB();

	@POST
	@Path("/getCatalogo")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public CatalogoResponse getCatalogo(Catalogo request) {
		CatalogoResponse response = new CatalogoResponse();
		response = CatalogoDB.getCatalogoByTable(request);
		
		return response;
	}
	
}
