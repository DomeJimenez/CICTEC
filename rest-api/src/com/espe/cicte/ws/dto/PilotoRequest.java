package com.espe.cicte.ws.dto;

import java.sql.Blob;

public class PilotoRequest {
	private String id;
	private String id_mil;
	private Blob foto;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_mil() {
		return id_mil;
	}
	public void setId_mil(String id_mil) {
		this.id_mil = id_mil;
	}
	public Blob getFoto() {
		return foto;
	}
	public void setFoto(Blob foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "PilotoRequest [id=" + id + ", id_mil=" + id_mil + ", foto=" + foto + "]";
	}
	
	
}
