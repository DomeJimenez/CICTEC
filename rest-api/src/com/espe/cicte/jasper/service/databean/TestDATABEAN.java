package com.espe.cicte.jasper.service.databean;

import java.util.List;

import com.espe.cicte.jasper.service.dto.ScoreDTO;

public class TestDATABEAN {
	private String nombre;
	private List<ScoreDTO> scores;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

	public List<ScoreDTO> getScores() {
		return scores;
	}

	public void setScores(List<ScoreDTO> scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		return "TestDATABEAN [nombre=" + nombre + ", scores=" + scores + "]";
	}

	
	
}
