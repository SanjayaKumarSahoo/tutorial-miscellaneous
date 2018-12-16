package com.example.elastic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.elastic.document.DepartmentDocument;

public interface DepartmentDocumentService {

	public void save(DepartmentDocument department);

	public Page<DepartmentDocument> findByEmployeeDocumentsName(String name, PageRequest pageable);
	
	public Page<DepartmentDocument> findByEmployeeDocumentsNameUsingCustomQuery(String name, Pageable pageable);

}
