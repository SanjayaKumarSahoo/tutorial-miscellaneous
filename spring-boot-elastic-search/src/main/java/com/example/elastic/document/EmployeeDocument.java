package com.example.elastic.document;

public class EmployeeDocument {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EmployeeDocument [name=" + name + "]";
	}
	
}
