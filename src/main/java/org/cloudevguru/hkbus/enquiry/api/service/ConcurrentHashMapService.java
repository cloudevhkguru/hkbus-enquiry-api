package org.cloudevguru.hkbus.enquiry.api.service;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDto;

public interface ConcurrentHashMapService {

	List<ManagedRouteDto> getRouteListFromRouteListChmByRoute(String route);
	
	List<ManagedRouteDto> getRouteListFromRouteListChmByRouteKey(String routeKey);

	List<ManagedRouteDto> putRouteListToRouteListChm(String routeKey, List<ManagedRouteDto> dtos);
	
	Boolean isEmptyRouteListChm();
	
	void cleanRouteListChm();

}
