package org.cloudevguru.hkbus.enquiry.api.controller;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDetailResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedStopResponse;
import org.cloudevguru.hkbus.enquiry.api.manager.ManagedManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(value = "ctbnwfb-passthrough", description = "This API is to provide services for bus route of KMB, CTB and NWFBB with uniformed path and response.")
public class ManagedController {

	@Autowired
	private ManagedManager managedManager;

	//route
	@GetMapping(path = "/route/{company}/{route}")
	public ManagedRouteListResponse getRouteListByCompanyByRoute(@PathVariable("company") String company,@PathVariable("route") String route) {
		return managedManager.getRouteListByCompanyAndRoute(company,route);
	}
	
	@GetMapping(path = "/route/{company}/{route}/{direction}")
	public ManagedRouteResponse getRouteByCompanyAndRouteAndDirection(@PathVariable("company") String company,@PathVariable("route") String route,@PathVariable("direction") String direction) {
		return managedManager.getRouteByCompanyAndRouteAndDirection(company,route,direction);
	}
	
	@GetMapping(path = "/stop/{company}/{stopId}")
	public ManagedStopResponse getStopByCompanyAndStopId(@PathVariable("company") String company,@PathVariable("stopId") String stopId) {
		return managedManager.getStopByCompanyAndStopId(company,stopId);
	}
	
	@GetMapping(path = "/route-stop/{company}/{route}/{direction}")
	public ManagedRouteStopListResponse getRouteStopListByCompanyAndRouteAndDirection(@PathVariable("company") String company,@PathVariable("route") String route,@PathVariable("direction") String direction) {
		return managedManager.getRouteStopListByCompanyAndRouteAndDirection(company,route,direction);
	}
	
	@GetMapping(path = "/route-detail/{company}/{route}/{direction}")
	public ManagedRouteDetailResponse getRouteDetailByCompanyAndRouteAndDirection(@PathVariable("company") String company,@PathVariable("route") String route,@PathVariable("direction") String direction) {
		return managedManager.getRouteDetailByCompanyAndRouteAndDirection(company,route,direction);
	}
	
}
