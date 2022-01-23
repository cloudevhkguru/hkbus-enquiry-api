package org.cloudevguru.hkbus.enquiry.api.service;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDetailResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDto;

public interface ConcurrentHashMapService {

	List<ManagedRouteDto> getRouteListFromRouteListChmByRouteStartWith(String route);
	
	List<ManagedRouteDto> getRouteListFromRouteListChmByRouteKey(String routeKey);

	List<ManagedRouteDto> putRouteListToRouteListChm(String routeKey, List<ManagedRouteDto> dtos);
	
	ManagedRouteDetailResponse getRouteDetailResponseFromRouteDetailChm(String routeDetailKey);
	
	ManagedRouteDetailResponse putRouteDetailResponseToRouteDetailChm(String routeDetailKey,ManagedRouteDetailResponse response);
	
	Boolean isEmptyRouteListChm();
	
	void cleanRouteListChm();
	
	void cleanRouteDetailChm();

}
