package org.cloudevguru.hkbus.enquiry.api.controller;

import java.util.Date;

import org.cloudevguru.hkbus.enquiry.api.manager.SystemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

	@Autowired
	private SystemManager systemManager;

	@GetMapping(path = "/system/healthcheck")
	public String healthCheck() {
		systemManager.preloadRouteList(); // Load Balancer help to preload Route List during health-check
		return String.format("It works and preload route-list at %s", new Date());
	}

	@GetMapping(path = "/system/cacheEvict")
	public String cacheAllEvict() {
		systemManager.cacheAllEvict();
		return String.format("All cache cleared at %s!", new Date());
	}

}
