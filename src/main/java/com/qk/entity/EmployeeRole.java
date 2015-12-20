package com.qk.entity;

public enum EmployeeRole {

	MANAGER("01", "Manager"), PRINCIPAL("02", "Principal Consultant"), Consultant(
			"03", "Consultant");

	private String id;
	private String description;

	EmployeeRole(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}
