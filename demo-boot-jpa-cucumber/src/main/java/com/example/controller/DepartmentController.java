package com.example.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Department;
import com.example.service.DepartmentService;
import com.response.Fault;
import com.response.Response;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value = "/post", produces = APPLICATION_JSON_VALUE, consumes =  TEXT_PLAIN_VALUE, method = POST)
	public @ResponseBody Response<Long> createDepartment(@RequestBody String departmentName) {
		Response<Long> response = new Response<>();
		try {
			Department deptartment = new Department();
			deptartment.setName(departmentName);
			long id = departmentService.save(deptartment);
			response.setData(id);
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
		
	@RequestMapping(value = "/getAll", produces = APPLICATION_JSON_VALUE, method = GET)
	public @ResponseBody Response<List<String>> getAllDepartments() {
		Response<List<String>> response = new Response<>();
		try {
			List<Department> departments = departmentService.getAllDepartments();

			List<String> departmentNames = new ArrayList<>();

			for (Department department : departments) {
				departmentNames.add(department.getName());
			}

			response.setData(departmentNames);
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
