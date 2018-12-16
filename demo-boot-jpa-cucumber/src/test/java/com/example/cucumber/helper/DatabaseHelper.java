package com.example.cucumber.helper;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

@Service
public class DatabaseHelper {

	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;

	@Autowired
	public DatabaseHelper(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
	}

	@Transactional(propagation = REQUIRES_NEW)
	public void deleteData() {
		employeeRepository.deleteAll();
		departmentRepository.deleteAll();
	}
}
