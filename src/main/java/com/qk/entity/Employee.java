package com.qk.entity;

import java.util.UUID;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee extends ResourceSupport {

	private String eId;
	private String firstName;
	private String lastName;

	// private EmployeeRole role;

	@JsonCreator
	public Employee(@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName) {

		eId = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEId() {
		if (eId == null) {
			eId = UUID.randomUUID().toString();
		}
		return eId;
	}

	// public EmployeeRole getRole() {
	// return role;
	// }
	//
	// public void setRole(EmployeeRole role) {
	// this.role = role;
	// }
}
