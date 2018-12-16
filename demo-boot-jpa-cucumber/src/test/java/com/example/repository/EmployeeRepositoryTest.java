package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.DemoApplication;
import com.example.entity.Department;
import com.example.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@Transactional
@Rollback
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Test
	public void testSaveEmployee() {

		// given
		Department department = new Department();
		department.setName("MARKETING");
		Department savedDepartment = departmentRepository.save(department);
		
		Employee employee = new Employee();
		employee.setName("HARRY");		
		employee.setDepartment(savedDepartment);
		
		// when
		employeeRepository.save(employee);

		// then
		List<Employee> employees = employeeRepository.findAll();
		Assert.assertEquals(employees.size(), 1);
		Assert.assertEquals(employees.get(0).getName(), "HARRY");
		Assert.assertEquals(employees.get(0).getDepartment().getName(), "MARKETING");
	}

	
	@Test
	public void testGetAllEmployees() {

		// given
		Department department = new Department();
		department.setName("SALES");
		Department depart = departmentRepository.save(department);

		// when
		Employee employee = new Employee();
		employee.setName("HARRY");
		employee.setDepartment(depart);
		Employee emp1 = employeeRepository.save(employee);

		// then
		List<Employee> employees = employeeRepository.findAll();
		Assert.assertEquals(employees.size(), 1);
		Assert.assertEquals(emp1.getName(), employees.get(0).getName());
	}

	
	@Test
	public void testFindEmployeeByName(){
		// given
		Department department = new Department();
		department.setName("SALES");
		Department depart = departmentRepository.save(department);
		Employee employee = new Employee();
		employee.setName("HARRY");
		employee.setDepartment(depart);
		Employee emp1 = employeeRepository.save(employee);
		
		// when
		Employee emp =  employeeRepository.findByName("HARRY");
		
		// then
		Assert.assertEquals(emp.getName(), "HARRY");
		Assert.assertEquals(emp.getDepartment().getName(), "SALES");
	}
	
	@Test
	public void testGetEmployeeById() {

		// given
		Department department = new Department();
		department.setName("SALES");
		Department depart = departmentRepository.save(department);

		// when
		Employee employee = new Employee();
		employee.setName("HARRY");
		employee.setDepartment(depart);
		Employee emp1 = employeeRepository.save(employee);

		// then
		Employee emp2 = employeeRepository.findOne(emp1.getId());
		Assert.assertEquals(emp1.getName(), emp2.getName());
		Assert.assertEquals(emp1.getDepartment().getName(), emp1.getDepartment().getName());
	}

}
