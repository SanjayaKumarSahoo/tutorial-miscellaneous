package com.example.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Department;
import com.example.repository.DepartmentRepository;

@Transactional
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public Long save(Department department) {
		Department storedDepartment = departmentRepository.save(department);
		return storedDepartment.getId();
	}

	@Override
	public Department getDepartmentById(Long id) {
		return departmentRepository.findOne(id);
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

}
