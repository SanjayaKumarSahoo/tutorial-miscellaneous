package com.example.dto;

import java.io.Serializable;
import java.util.List;

public class DepartmentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private List<EmployeeDTO> employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}
}
