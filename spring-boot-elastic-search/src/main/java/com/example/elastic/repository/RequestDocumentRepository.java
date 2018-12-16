package com.example.elastic.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.elastic.document.Request;

public interface RequestDocumentRepository extends ElasticsearchRepository<Request, Long> {

	public List<Request> findByRequestNameOrRequestDescAndCreatorCodeIn(String requestName, String requestDesc, List<String> codes);
	
	public List<Request> findByCreatorCodeInAndRequestNameOrRequestDesc(List<String> codes, String requestName, String requestDesc);
	
	
	public List<Request> findByCreatorCodeInAndRequestNameOrCreatorCodeInAndRequestDesc(List<String> codes,
			String requestName, List<String> codes1, String requestDesc);
	
	public List<Request> findByRequestNameAndCreatorCodeInOrRequestDescAndCreatorCodeIn(String requestName, List<String> codes, String requestDesc, List<String> codes1);
	
/*	
	public List<Request> findByRequestNameOrRequestDescByCreatorCodeIn(String requestName, String requestDesc,
			List<String> codes);
	*/
	
	
}
