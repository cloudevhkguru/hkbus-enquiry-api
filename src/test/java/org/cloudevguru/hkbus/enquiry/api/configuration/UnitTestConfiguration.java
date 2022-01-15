package org.cloudevguru.hkbus.enquiry.api.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class UnitTestConfiguration {

	@Bean
	public static String testKMBRoute1A() {
		return "1A";
	}
	
	@Bean
	public static String testKMBRoute101() {
		return "101";
	}
	
	@Bean
	public static String testKMBCircularRoute27() {
		return "27";
	}
	
	@Bean
	public static String testKMBStop() {
		return "18492910339410B1";
	}
	
	@Bean
	public static String testKMB1AInboundStop() {
		return "775FC7E2B86C4E3F";
	}
	
	@Bean
	public static String testCTBRouteA29() {
		return "A29";
	}
	
	@Bean
	public static String testCTBRoute671() {
		return "671";
	}
	
	@Bean
	public static String testCTBCircularRoute12A() {
		return "12A";
	}
	
	public static String testCTBA29InboundStop() {
		return "003540";
	}
	
	@Bean
	public static String testNWFBRoute15C() {
		return "15C";
	}
	
	@Bean
	public static String testNWFBRoute18() {
		return "18";
	}
	
	@Bean
	public static String testNWFB18OutboundStop() {
		return "002964";
	}
	
	@Bean
	public static String testCTBNWFBStop() {
		return "002737";
	}
	
}
