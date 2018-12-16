package com.response;

public class Response<T> {

	private Fault fault;
	private T t;

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}

	public T getData() {
		return t;
	}

	public void setData(T t) {
		this.t = t;
	}

}
