package com.espe.cicte.ws.dto;

public class Menu {

	private int parent;
	private String name;
	private String description;
	private String url;
	private int order;
	private boolean nfinal;
	private int option;
	
	
	
	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isNfinal() {
		return nfinal;
	}

	public void setNfinal(boolean nfinal) {
		this.nfinal = nfinal;
	}
}
