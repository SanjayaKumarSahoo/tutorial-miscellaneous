package com.example.cucumber.steps;

import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.cucumber.helper.DatabaseHelper;
import com.example.dto.EmployeeDTO;
import com.response.Response;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PersistEmployeeStepsdef extends AbstractStep {

	@Autowired
	private DatabaseHelper databaseHelper ;
		

	@Before
	public void beforeScenario() {
		// TODO if any
	}
	
	private final RestTemplate restTemplate = new RestTemplate();

	@Given("^the department (.*) exist in system :$")
	public void createDepartmetnInSystem(String department) {
		@SuppressWarnings("unchecked")
		Response<Long> response = restTemplate.postForObject("http://localhost:8080/department/post", department, Response.class);
		Assert.assertNotNull(response.getData());
	}	

	@Then("^I called /post endpoint to save employee (.*) in (.*) department :$")
	public void callPostToPersistEmployee(String employeeName, String departmentName) {

		EmployeeDTO employee =  new EmployeeDTO();
		employee.setName(employeeName);
		employee.setDepartmentName(departmentName);
		
		// call rest end point to persist
		@SuppressWarnings("unchecked")
		Response<Long> response = restTemplate.postForObject("http://localhost:8080/employee/post", employee, Response.class);
		Assert.assertNotNull(response.getData());
	}

	@Then("^I called /get/(.*) endpoint to get all employees and I received name and department as : (.*)$")
	public void callGetEmployeeDetailByName(String empName, List<String> employeeNameAndDepartmentName) {
		@SuppressWarnings("unchecked")
		ParameterizedTypeReference<Response<EmployeeDTO>> typeRef = new ParameterizedTypeReference<Response<EmployeeDTO>>() {};

		ResponseEntity<Response<EmployeeDTO>> responseEntity = restTemplate.exchange("http://localhost:8080/employee/get/"+empName, HttpMethod.GET, null, typeRef);
		Response<EmployeeDTO> response =  responseEntity.getBody();
		Assert.assertEquals(response.getData().getName(), employeeNameAndDepartmentName.get(0));
		Assert.assertEquals(response.getData().getDepartmentName(), employeeNameAndDepartmentName.get(1));
	}

	@After
	public void afterScenario() {
		databaseHelper.deleteData();
	}
}
