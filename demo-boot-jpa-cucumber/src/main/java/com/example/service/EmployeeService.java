package com.example.service;

import java.util.List;

import com.example.entity.Employee;

public interface EmployeeService {

	public Long saveEmployee(Employee employee);

	public Employee getEmployeeByName(String name);
	
	public List<Employee> getAllEmployees();

}
