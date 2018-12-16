package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;

@Transactional
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Long saveEmployee(Employee employee) {
		Department department =  departmentRepository.findByName(employee.getDepartment().getName());
		employee.setDepartment(department);
		return employeeRepository.save(employee).getId();
	}

	@Override
	public Employee getEmployeeByName(String name) {	
		return employeeRepository.findByName(name);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
}
