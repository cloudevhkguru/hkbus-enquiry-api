package org.cloudevguru.hkbus.enquiry.api.controller;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDetailResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteStopEtaResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedStopResponse;
import org.cloudevguru.hkbus.enquiry.api.manager.ManagedManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "${cros.allow.origin}")
@RestController
@Api(value = "ctbnwfb-passthrough", description = "This API is to provide services for bus route of KMB, CTB and NWFB with uniformed path and response.")
public class ManagedController {

	@Autowired
	private ManagedManager managedManager;

	// route
	@GetMapping(path = "/route")
	public ManagedRouteListResponse getAllRouteList() {
		return managedManager.getAllRoute();
	}

	@GetMapping(path = "/route/{route}")
	public ManagedRouteListResponse getRouteListByRoute(@PathVariable("route") String route) {
		return managedManager.getRouteByRoute(route);
	}

	@GetMapping(path = "/route/{company}/{route}/{serviceType}")
	public ManagedRouteListResponse getRouteListByCompanyByRouteAndServiceType(@PathVariable("company") String company,
			@PathVariable("route") String route,
			@PathVariable(name = "serviceType", required = false) String serviceType) {
		return managedManager.getRouteListByCompanyAndRouteAndServiceType(company, route, serviceType);
	}

	@GetMapping(path = {"/route/{company}/{route}/{direction}/", "/route/{company}/{route}/{direction}/{serviceType}"})
	public ManagedRouteResponse getRouteByCompanyAndRouteAndDirectionAndServiceType(
			@PathVariable("company") String company, @PathVariable("route") String route,
			@PathVariable("direction") String direction,
			@PathVariable(name = "serviceType") String serviceType) {
		return managedManager.getRouteByCompanyAndRouteAndDirectionAndServiceType(company, route, direction,
				serviceType);
	}

	@GetMapping(path = "/stop/{company}/{stopId}")
	public ManagedStopResponse getStopByCompanyAndStopId(@PathVariable("company") String company,
			@PathVariable("stopId") String stopId) {
		return managedManager.getStopByCompanyAndStopId(company, stopId);
	}

	@GetMapping(path = "/route-stop/{company}/{route}/{direction}/{serviceType}")
	public ManagedRouteStopListResponse getRouteStopListByCompanyAndRouteAndDirectionAndServiceType(
			@PathVariable("company") String company, @PathVariable("route") String route,
			@PathVariable("direction") String direction,
			@PathVariable(name = "serviceType", required = false) String serviceType) {
		return managedManager.getRouteStopListByCompanyAndRouteAndDirectionAndServiceType(company, route, direction,
				serviceType);
	}

	@GetMapping(path = "/route-detail/{company}/{route}/{direction}/{serviceType}")
	public ManagedRouteDetailResponse getRouteDetailByCompanyAndRouteAndDirectionAndServiceType(
			@PathVariable("company") String company, @PathVariable("route") String route,
			@PathVariable("direction") String direction,
			@PathVariable(name = "serviceType", required = false) String serviceType) {
		return managedManager.getRouteDetailByCompanyAndRouteAndDirectionAndServiceType(company, route, direction,
				serviceType);
	}

	@GetMapping(path = "/route-stop-eta/{company}/{stopId}/{route}/{direction}/{serviceType}")
	public ManagedRouteStopEtaResponse getRouteStopEtaByCompanyAndStopIdAndRouteAndDirectionAndServiceType(
			@PathVariable("company") String company, @PathVariable("stopId") String stopId,
			@PathVariable("route") String route, @PathVariable("direction") String direction,
			@PathVariable(name = "serviceType", required = false) String serviceType) {
		return managedManager.getRouteStopEtaByCompanyAndStopIdAndRouteAndDirectionAndServiceType(company, stopId,
				route, direction, serviceType);
	}

}
