package org.cloudevguru.hkbus.enquiry.api.service;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDetailResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.routefare.v1.RouteFareDto;

public interface ConcurrentHashMapService {
	
	List<ManagedRouteDto> getRouteListFromRouteListChmByRouteStartWith(String route);
	
	List<ManagedRouteDto> getRouteListFromRouteListChmByRouteKey(String routeKey);

	List<ManagedRouteDto> putRouteListToRouteListChm(String routeKey, List<ManagedRouteDto> dtos);
	
	ManagedRouteDetailResponse getRouteDetailResponseFromRouteDetailChm(String routeDetailKey);
	
	ManagedRouteDetailResponse putRouteDetailResponseToRouteDetailChm(String routeDetailKey,ManagedRouteDetailResponse response);
	
	List<RouteFareDto> getAllRouteFareDtoFromrouteFareDtoChm();
	
	RouteFareDto getRouteFareDtoFromrouteFareDtoChmByRouteFareKey(String routeFareKey);
	
	RouteFareDto putRouteFareDtoTorouteFareDtoChm(String routeFareKey,RouteFareDto routeFareDto);
	
	Boolean isEmptyRouteListChm();
	
	Boolean isEmptyRouteFareDtoChm();
	
	void cleanRouteListChm();
	
	void cleanRouteDetailChm();

	void cleanRouteFareChm();
}
