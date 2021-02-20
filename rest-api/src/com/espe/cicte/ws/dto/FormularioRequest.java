package com.espe.cicte.ws.dto;

public class FormularioRequest extends BaseRequest{

	private int id;
	private String nombre;
	private String codigo;
	private String fecha;
	private String detalle;
	private String estado;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "FormularioRequest [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", fecha=" + fecha
				+ ", detalle=" + detalle + ", estado=" + estado + "]";
	}
			
}
