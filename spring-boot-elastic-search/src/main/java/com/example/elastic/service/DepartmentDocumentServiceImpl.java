package com.example.elastic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.elastic.document.DepartmentDocument;
import com.example.elastic.repository.DepartmentDocumentRepository;


@Service
public class DepartmentDocumentServiceImpl implements DepartmentDocumentService {

	private final DepartmentDocumentRepository departmentElasticRepository;

	@Autowired
	public DepartmentDocumentServiceImpl(DepartmentDocumentRepository departmentElasticRepository) {
		this.departmentElasticRepository = departmentElasticRepository;
	}

	@Override
	public void save(DepartmentDocument departmentDomain) {
		departmentElasticRepository.save(departmentDomain);
	}

	@Override
	public Page<DepartmentDocument> findByEmployeeDocumentsName(String name, PageRequest pageable) {
		return departmentElasticRepository.findByEmployeeDocumentsName(name, pageable);
	}
	
	@Override
	public Page<DepartmentDocument> findByEmployeeDocumentsNameUsingCustomQuery(String name, Pageable pageable) {		
		return departmentElasticRepository.findByEmployeeDocumentsNameUsingCustomQuery(name, pageable);
	}

}
