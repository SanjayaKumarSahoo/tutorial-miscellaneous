package com.app;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

/**
 * Created by sanjaya on 11/12/16.
 */
public class SaveBase {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new EmployeeController());
    }

    public void assertThatRejectionReasonIsNull(Object rejectionReason) {
        assert rejectionReason == null;
    }


}
