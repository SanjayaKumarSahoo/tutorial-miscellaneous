package com.app;

import com.app.model.Employee;
import com.app.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by sanjaya on 11/12/16.
 */
@Service
public class EmployeeService {

    private final RestTemplate restTemplate;
    private int port = 8080;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Response saveEmployee(Employee employee) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
        ResponseEntity<Response> response =
                restTemplate.exchange("http://localhost:" + port + "/employee/save", POST,
                        new HttpEntity<Object>(employee, httpHeaders),
                        Response.class);

        return response.getBody();
    }

}
