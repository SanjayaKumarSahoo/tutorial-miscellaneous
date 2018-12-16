package com.app;

import com.app.EmployeeService;
import com.app.model.Employee;
import com.app.model.Response;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

/**
 * Created by sanjaya on 11/12/16.
 */


@SpringBootTest(webEnvironment = NONE)
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = {"spring-cloud-contract-producer:http-producer:+:stubs:8080"}, workOffline = true)
@DirtiesContext
public class EmployeeOperationsTest {


    @Autowired
    private EmployeeService employeeService;

    @Test
    public void saveEmployee() {
        // given:
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("TOM");

        // when:
        Response response = employeeService.saveEmployee(employee);

        // then:
        Assertions.assertThat(response.getMessage()).isEqualTo("Saved employee TOM");
    }
}
