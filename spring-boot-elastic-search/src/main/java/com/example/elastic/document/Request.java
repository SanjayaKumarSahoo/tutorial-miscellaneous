package com.example.elastic.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "request", type = "request")
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String requestName;

	private String requestDesc;

	@Field(type = FieldType.Object)
	private Creator creator;

	public String getRequestDesc() {
		return requestDesc;
	}

	public void setRequestDesc(String requestDesc) {
		this.requestDesc = requestDesc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", requestName=" + requestName + ", requestDesc=" + requestDesc + ", creator="
				+ creator + "]";
	}

}
