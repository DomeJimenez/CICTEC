package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class FormularioResponse extends BaseResponse{

	private Formulario[] formulario;

	public Formulario[] getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario[] formulario) {
		this.formulario = formulario;
	}

	@Override
	public String toString() {
		return "FormularioResponse [formulario=" + Arrays.toString(formulario) + "]";
	}

}
