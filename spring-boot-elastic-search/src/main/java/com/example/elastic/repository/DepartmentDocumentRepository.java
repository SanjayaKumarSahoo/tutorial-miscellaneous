package com.example.elastic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.elastic.document.DepartmentDocument;

public interface DepartmentDocumentRepository extends ElasticsearchRepository<DepartmentDocument, Long> {

	@Query("{\"bool\": {\"must\": [{\"match\": {\"employeeDocuments.name\": \"?0\"}}]}}")
	public Page<DepartmentDocument> findByEmployeeDocumentsNameUsingCustomQuery(String name, Pageable pageable);

	public Page<DepartmentDocument> findByEmployeeDocumentsName(String name, Pageable pageable);
	
	public List<DepartmentDocument> findByEmployeeDocumentsNameIn(List<String> names);
	
	public List<DepartmentDocument> findByNameIn(Collection<String> names);
	
	public List<DepartmentDocument> findByNameAndEmployeeDocumentsNameIn(String name, List<String> names);
	
	public List<DepartmentDocument> findByNameOrIdAndEmployeeDocumentsNameIn(String name, long id, List<String> names);	
}
