package org.cloudevguru.hkbus.enquiry.api.service;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDetailResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.routefare.v1.RouteFareDto;

public interface ConcurrentHashMapService {
	
	ManagedRouteDto getRawRouteFromRawRouteChmByRouteKey(String routeKey);
	
	List<ManagedRouteDto> getAllRawRouteFromRawRouteChm();
	
	List<ManagedRouteDto> getRawRouteFromRawRouteChmByRouteStartWith(String route);
	
	ManagedRouteDto putRawRouteToRawRouteChm(String routeKey, ManagedRouteDto managedRouteDto);
	
	List<ManagedRouteDto> getRouteListFromRouteListChmByRouteKey(String routeKey);

	List<ManagedRouteDto> putRouteListToRouteListChm(String routeKey, List<ManagedRouteDto> dtos);
	
	ManagedRouteDetailResponse getRouteDetailResponseFromRouteDetailChm(String routeDetailKey);
	
	ManagedRouteDetailResponse putRouteDetailResponseToRouteDetailChm(String routeDetailKey,ManagedRouteDetailResponse response);
	
	List<RouteFareDto> getAllRouteFareDtoFromrouteFareDtoChm();
	
	RouteFareDto getRouteFareDtoFromrouteFareDtoChmByRouteFareKey(String routeFareKey);
	
	RouteFareDto putRouteFareDtoTorouteFareDtoChm(String routeFareKey,RouteFareDto routeFareDto);
	
	Boolean isEmptyRawRouteChm();
	
	Boolean isEmptyRouteListChm();
	
	Boolean isEmptyRouteFareDtoChm();
	
	void cleanRawRouteChm();
	
	void cleanRouteListChm();
	
	void cleanRouteDetailChm();

	void cleanRouteFareChm();
}
