package com.example.service;

import java.util.List;

import com.example.entity.Department;

public interface DepartmentService {

	public Long save(Department department);

	public Department getDepartmentById(Long id);
	
	public List<Department> getAllDepartments(); 
}
