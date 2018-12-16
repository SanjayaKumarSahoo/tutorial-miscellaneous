package com.example.cucumber.steps;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.DemoApplication;
import com.example.dto.EmployeeDTO;
import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;
import com.response.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@Transactional
@Rollback
@Ignore
public class SampleTest {

	@Autowired
	private DepartmentService departmentService;
	
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@Test
	@Ignore
	public void testDept() {
		Department department = new Department();
		department.setName("SALES");
		departmentService.save(department);

		Employee employee = new Employee();
		employee.setName("HARRY");
		Department d = new Department();
		d.setName("SALES");
		employee.setDepartment(d);

		long id = employeeService.saveEmployee(employee);

		List<Employee> employees = employeeService.getAllEmployees();

		System.out.println(employees);
	}
	
	@Test
	public void test() {
		RestTemplate restTemplate = new RestTemplate();

		String str = new String("SALES");
		Response<Long> response = restTemplate.postForObject("http://127.0.0.1:8080/department/post", str, Response.class);
		
		EmployeeDTO dto = new EmployeeDTO();
		dto.setDepartmentName("SALES");
		dto.setName("HARRY");
		
		Response<Long> response1 = restTemplate.postForObject("http://127.0.0.1:8080/employee/post", dto, Response.class);
		
		System.out.println(response1);
		
		/*employee = new Employee();
		employee.setName("HARRY");
		department = new Department();
		department.setName("MARKETING");
		employee.setDepartment(department);
		Response<Long> response1 = restTemplate.postForObject("http://127.0.0.1:8080/employee/post", employee, Response.class);

		Response<List<Employee>> response2 = restTemplate.getForObject("http://127.0.0.1:8080/employee/getAll", Response.class);
		System.out.println(response2.getData().size());*/

	}
}
