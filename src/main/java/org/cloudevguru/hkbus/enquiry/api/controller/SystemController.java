package org.cloudevguru.hkbus.enquiry.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

	@GetMapping(path = "/system/healthcheck")
	public String healthCheck() {
		return "It works!";
	}

}
