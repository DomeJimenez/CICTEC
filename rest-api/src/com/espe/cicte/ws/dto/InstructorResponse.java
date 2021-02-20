package com.espe.cicte.ws.dto;

import java.util.Arrays;

public class InstructorResponse extends BaseResponse {
	private Instructor[] instructor;

	public Instructor[] getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor[] instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "InstructorResponse [instructor=" + Arrays.toString(instructor) + "]";
	}
	
}