package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class CatalogoResponse extends BaseResponse{

	private Catalogo[] catalogo;

	public Catalogo[] getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo[] catalogo) {
		this.catalogo = catalogo;
	}

	@Override
	public String toString() {
		return "CatalogoResponse [catalogo=" + Arrays.toString(catalogo) + "]";
	}
	
	
	
	
}
