package org.cloudevguru.hkbus.enquiry.api;

import java.util.Date;

import org.cloudevguru.hkbus.enquiry.api.manager.SystemManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseTestEntity {

	@Autowired
	private SystemManager systemManager;

	@BeforeEach
	public void printTestNameBeforeTest(TestInfo testInfo) {
		System.out.println(String.format("Start %s at %s", testInfo.getDisplayName(), new Date()));
	}

	@AfterEach
	public void printTestNameAfterTest(TestInfo testInfo) {
		systemManager.cacheAllEvict();
		System.out.println(String.format("Completed %s at %s", testInfo.getDisplayName(), new Date()));
	}

}
