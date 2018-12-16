package com.example.elastic.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "department", type = "department")
public class DepartmentDocument {

	@Id
	private long id;

	private String name;

	@Field(type = FieldType.Nested)
	private List<EmployeeDocument> employeeDocuments;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EmployeeDocument> getEmployeeDocuments() {
		return employeeDocuments;
	}

	public void setEmployeeDocuments(List<EmployeeDocument> employeeDocuments) {
		this.employeeDocuments = employeeDocuments;
	}

	@Override
	public String toString() {
		return "DepartmentDocument [id=" + id + ", name=" + name + ", employeeDocuments=" + employeeDocuments + "]";
	}
	
}
