package com.example.repository;

import java.util.Arrays;
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
public class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Test
	public void testSaveWithCascade() {

		// given
		// create department
		Department department = new Department();
		department.setName("SALES");

		// create employee
		Employee employee = new Employee();
		employee.setName("HARRY");
		employee.setDepartment(department);
		department.setEmployees(Arrays.asList(employee));

		// when
		Department depart = departmentRepository.save(department);

		// then
		List<Department> departments = departmentRepository.findAll();
		Assert.assertTrue(departments.size() == 1);
		Assert.assertTrue(departments.get(0).getEmployees().size() == 1);
		Assert.assertNotNull(depart.getId());
	}

	@Test
	public void testSaveDepartmentOnly() {

		// given
		// create department
		Department department = new Department();
		department.setName("SALES");

		// when
		Long departmentId = departmentRepository.save(department).getId();

		// then
		Department foundDepartment = departmentRepository.findOne(departmentId);
		Assert.assertEquals(foundDepartment.getName(), "SALES");
		Assert.assertNull(foundDepartment.getEmployees());
	}

	@Test
	public void testGetDepartmentById() {

		// given
		// create department
		Department department = new Department();
		department.setName("MARKETING");
		// create employee
		Employee employee = new Employee();
		employee.setName("HARRY");
		employee.setDepartment(department);
		department.setEmployees(Arrays.asList(employee));
		Department depart = departmentRepository.save(department);

		// when
		Department data = departmentRepository.findOne(depart.getId());

		// then
		Assert.assertTrue(data.getEmployees().size() == 1);
		Assert.assertEquals(data.getName(), "MARKETING");
		Assert.assertEquals(data.getEmployees().get(0).getName(), "HARRY");
	}

	@Test
	public void findDepartmentByName() {
		// given
		Department department = new Department();
		department.setName("SALES");
		Long id = departmentRepository.save(department).getId();
		
		// when
		Department dept =  departmentRepository.findByName("SALES");
		
		// then
		Assert.assertEquals(id, dept.getId());
	}
}
