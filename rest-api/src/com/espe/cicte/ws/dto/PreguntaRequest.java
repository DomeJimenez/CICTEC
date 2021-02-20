package com.espe.cicte.ws.dto;

public class PreguntaRequest extends BaseRequest{
	
	private int codigo;
	private String criterio;
	private String estado;
	private Formulario formulario;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getCriterio() {
		return criterio;
	}
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Formulario getFormulario() {
		return formulario;
	}
	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}
	@Override
	public String toString() {
		return "PreguntaRequest [codigo=" + codigo + ", criterio=" + criterio + ", estado=" + estado + ", formulario="
				+ formulario + "]";
	}
	
	
	
}
