package org.cloudevguru.hkbus.enquiry.api.service;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.dto.routefare.v1.RouteFareDto;

public interface RouteFareService {

	List<RouteFareDto> getAllBusesRouteFare();
}
