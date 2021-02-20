package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class PlanResponse extends BaseResponse{

	private Plan[] plan;

	public Plan[] getPlan() {
		return plan;
	}

	public void setPlan(Plan[] plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "PlanResponse [plan=" + Arrays.toString(plan) + "]";
	}
	
	
	
	
 }
