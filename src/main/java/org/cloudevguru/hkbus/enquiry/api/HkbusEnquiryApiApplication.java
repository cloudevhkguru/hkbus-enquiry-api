package org.cloudevguru.hkbus.enquiry.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages= {"org.cloudevguru.hkbus.enquiry.api"})
public class HkbusEnquiryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HkbusEnquiryApiApplication.class, args);
	}

}
