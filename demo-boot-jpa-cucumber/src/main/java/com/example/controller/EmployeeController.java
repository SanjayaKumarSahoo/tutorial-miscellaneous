package com.example.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EmployeeDTO;
import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.response.Fault;
import com.response.Response;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "/post", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, method = POST)
	public @ResponseBody Response<Long> saveEmployeeByDepartment(@RequestBody EmployeeDTO employeeDTO) {
		Response<Long> response = new Response<>();
		try {

			Department dept = new Department();
			dept.setName(employeeDTO.getDepartmentName());

			Employee emp = new Employee();
			emp.setName(employeeDTO.getName());
			emp.setDepartment(dept);

			long id = employeeService.saveEmployee(emp);
			response.setData(id);
			response.setFault(null);
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			Fault fault = new Fault();
			fault.setErrorMessage("ERROR");
			fault.setTrace(e.getMessage());
			response.setFault(fault);
			return response;
		}
	}

	@RequestMapping(value = "/get/{name}", produces = APPLICATION_JSON_VALUE, method = GET)
	public @ResponseBody Response<EmployeeDTO> getEmployeetByName(@PathVariable("name") String name) {
		Response<EmployeeDTO> response = new Response<>();
		try {
			Employee employee = employeeService.getEmployeeByName(name);

			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setName(employee.getName());
			employeeDTO.setDepartmentName(employee.getDepartment().getName());
			response.setData(employeeDTO);
			response.setFault(null);
			return response;

		} catch (Exception e) {
			Fault fault = new Fault();
			fault.setErrorMessage("ERROR");
			fault.setTrace(e.getMessage());
			response.setFault(fault);
			return response;
		}
	}

}
