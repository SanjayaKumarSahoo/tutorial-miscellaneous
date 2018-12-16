package com.example.elastic.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.BootAppLoader;
import com.example.elastic.document.DepartmentDocument;
import com.example.elastic.document.EmployeeDocument;
import com.example.elastic.repository.DepartmentDocumentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootAppLoader.class)
public class DepartmentDocumentRepositoryTest {

	@Value("${spring.data.elasticsearch.properties.path.home}")
	private String localElasticSearchDirectory;

	@Autowired
	private DepartmentDocumentRepository classUnderTest;

			
	@Test
	public void saveDepartmentDocumentToElasticSearch() {

		EmployeeDocument employeeDocument1 = new EmployeeDocument();
		employeeDocument1.setName("harry");

		EmployeeDocument employeeDocument2 = new EmployeeDocument();
		employeeDocument2.setName("sally");

		DepartmentDocument departmentDocument1 = new DepartmentDocument();
		departmentDocument1.setName("HR");
		departmentDocument1.setEmployeeDocuments(Arrays.asList(employeeDocument1, employeeDocument2));

		// given
		EmployeeDocument employeeDocument3 = new EmployeeDocument();
		employeeDocument3.setName("james");

		EmployeeDocument employeeDocument4 = new EmployeeDocument();
		employeeDocument4.setName("tom");

		DepartmentDocument departmentDocument2 = new DepartmentDocument();
		departmentDocument2.setName("MARKETING");
		departmentDocument2.setEmployeeDocuments(Arrays.asList(employeeDocument3, employeeDocument4));

		Iterable<DepartmentDocument> departmentDocuments = classUnderTest.save(Arrays.asList(departmentDocument1, departmentDocument2));
		Assert.assertEquals(Lists.newArrayList(departmentDocuments).size(), 2);

	}

	@Test
	public void givenPersistedDepartment_whenSearchByEmployeeName() {

		// given
		EmployeeDocument employeeDocument1 = new EmployeeDocument();
		employeeDocument1.setName("harry");

		EmployeeDocument employeeDocument2 = new EmployeeDocument();
		employeeDocument2.setName("sally");

		DepartmentDocument departmentDocument1 = new DepartmentDocument();
		departmentDocument1.setName("HR");
		departmentDocument1.setEmployeeDocuments(Arrays.asList(employeeDocument1, employeeDocument2));

		// given
		EmployeeDocument employeeDocument3 = new EmployeeDocument();
		employeeDocument3.setName("james");

		EmployeeDocument employeeDocument4 = new EmployeeDocument();
		employeeDocument4.setName("tom");

		DepartmentDocument departmentDocument2 = new DepartmentDocument();
		departmentDocument2.setName("MARKETING");
		departmentDocument2.setEmployeeDocuments(Arrays.asList(employeeDocument3, employeeDocument4));

		classUnderTest.save(Arrays.asList(departmentDocument1, departmentDocument2));

		// when
		final Page<DepartmentDocument> departmentByEmployee = classUnderTest.findByEmployeeDocumentsName("harry",
				new PageRequest(0, 10));
		assertEquals(1, departmentByEmployee.getTotalElements());
		assertEquals(departmentByEmployee.getContent().get(0).getName(), "HR");
	}

	@Test
	public void givenPersistedDepartment_whenSearchByEmployeeName_Custome_Query() {

		// given
		EmployeeDocument employeeDocument1 = new EmployeeDocument();
		employeeDocument1.setName("harry");

		EmployeeDocument employeeDocument2 = new EmployeeDocument();
		employeeDocument2.setName("sally");

		DepartmentDocument departmentDocument1 = new DepartmentDocument();
		departmentDocument1.setName("HR");
		departmentDocument1.setEmployeeDocuments(Arrays.asList(employeeDocument1, employeeDocument2));

		// given
		EmployeeDocument employeeDocument3 = new EmployeeDocument();
		employeeDocument3.setName("james");

		EmployeeDocument employeeDocument4 = new EmployeeDocument();
		employeeDocument4.setName("tom");

		DepartmentDocument departmentDocument2 = new DepartmentDocument();
		departmentDocument2.setName("MARKETING");
		departmentDocument2.setEmployeeDocuments(Arrays.asList(employeeDocument3, employeeDocument4));

		classUnderTest.save(Arrays.asList(departmentDocument1, departmentDocument2));

		// when
		final Page<DepartmentDocument> departmentByEmployee = classUnderTest
				.findByEmployeeDocumentsNameUsingCustomQuery("tom", new PageRequest(0, 10));
		assertEquals(1, departmentByEmployee.getTotalElements());
		assertEquals(departmentByEmployee.getContent().get(0).getName(), "MARKETING");
	}

	@After
	public void after() {
		classUnderTest.deleteAll();
	}

}
