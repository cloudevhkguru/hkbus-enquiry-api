package org.cloudevguru.hkbus.enquiry.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDetailResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.routefare.v1.RouteFareDto;
import org.cloudevguru.hkbus.enquiry.api.service.ConcurrentHashMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcurrentHashMapServiceImpl implements ConcurrentHashMapService {

	@Autowired
	private ConcurrentHashMap<String, ManagedRouteDto> rawRouteChm;

	@Autowired
	private ConcurrentHashMap<String, List<ManagedRouteDto>> managedRouteListResponseChm;

	@Autowired
	private ConcurrentHashMap<String, ManagedRouteDetailResponse> managedRouteDetailResponseChm;

	@Autowired
	private ConcurrentHashMap<String, RouteFareDto> routeFareDtoChm;
	
	@Override
	public ManagedRouteDto getRawRouteFromRawRouteChmByRouteKey(String routeKey) {
		return rawRouteChm.get(routeKey);
	}
	
	@Override
	public List<ManagedRouteDto> getAllRawRouteFromRawRouteChm(){
		List<ManagedRouteDto> dtos = new ArrayList<ManagedRouteDto>();
		rawRouteChm.forEachEntry(8, entry->{
			dtos.add(entry.getValue());
		});
		return dtos;
	}

	@Override
	public ManagedRouteDto putRawRouteToRawRouteChm(String routeKey, ManagedRouteDto managedRouteDto) {
		System.out.println("Creating raw-route record with key=" + routeKey + " at " + new Date());
		return rawRouteChm.putIfAbsent(routeKey, managedRouteDto);
	}

	@Override
	public List<ManagedRouteDto> getRawRouteFromRawRouteChmByRouteStartWith(String route) {
		List<ManagedRouteDto> dtos = new ArrayList<ManagedRouteDto>();
		rawRouteChm.forEachKey(8, k -> {
			String[] routeInfo = k.split("-");
			if (routeInfo.length > 1) {
				if (routeInfo[1].startsWith(route)) {
					ManagedRouteDto dto = rawRouteChm.get(k);
					dtos.add(dto);
				}
			}
		});
		return dtos;
	}

	@Override
	public List<ManagedRouteDto> getRouteListFromRouteListChmByRouteKey(String routeKey) {
		return managedRouteListResponseChm.get(routeKey);
	}

	@Override
	public List<ManagedRouteDto> putRouteListToRouteListChm(String routeKey, List<ManagedRouteDto> dtos) {
		System.out.println("Creating route-list-by-route record with key=" + routeKey + " at " + new Date());
		return managedRouteListResponseChm.putIfAbsent(routeKey, dtos);
	}

	@Override
	public ManagedRouteDetailResponse getRouteDetailResponseFromRouteDetailChm(String routeDetailKey) {
		return managedRouteDetailResponseChm.get(routeDetailKey);
	}

	@Override
	public ManagedRouteDetailResponse putRouteDetailResponseToRouteDetailChm(String routeDetailKey,
			ManagedRouteDetailResponse response) {
		System.out.println("Creating route-detail record with key=" + routeDetailKey + " at " + new Date());
		return managedRouteDetailResponseChm.putIfAbsent(routeDetailKey, response);
	}

	@Override
	public List<RouteFareDto> getAllRouteFareDtoFromrouteFareDtoChm() {
		List<RouteFareDto> routeFareDtos = new ArrayList<RouteFareDto>();
		routeFareDtoChm.forEachEntry(8, (entry) -> {
			routeFareDtos.add(entry.getValue());
		});
		return routeFareDtos;
	}

	@Override
	public RouteFareDto getRouteFareDtoFromrouteFareDtoChmByRouteFareKey(String routeFareKey) {
		return routeFareDtoChm.get(routeFareKey);
	}

	@Override
	public RouteFareDto putRouteFareDtoTorouteFareDtoChm(String routeFareKey, RouteFareDto routeFareDto) {
		System.out.println("Creating route-fare record with key=" + routeFareKey + " at " + new Date());
		return routeFareDtoChm.putIfAbsent(routeFareKey, routeFareDto);
	}

	@Override
	public Boolean isEmptyRawRouteChm() {
		return rawRouteChm.isEmpty();
	}

	@Override
	public Boolean isEmptyRouteListChm() {
		return managedRouteListResponseChm.isEmpty();
	}

	@Override
	public Boolean isEmptyRouteFareDtoChm() {
		return routeFareDtoChm.isEmpty();
	}

	@Override
	public void cleanRawRouteChm() {
		System.out.println("Start cleaning rawRouteChm at " + new Date());
		rawRouteChm.forEachKey(4, k -> {
			rawRouteChm.remove(k);
		});
	}

	@Override
	public void cleanRouteListChm() {
		System.out.println("Start cleaning routeListChm at " + new Date());
		managedRouteListResponseChm.forEachKey(4, k -> {
			managedRouteListResponseChm.remove(k);
		});
	}

	@Override
	public void cleanRouteDetailChm() {
		System.out.println("Start cleaning routeDetailChm at " + new Date());
		managedRouteDetailResponseChm.forEachKey(4, k -> {
			managedRouteDetailResponseChm.remove(k);
		});
	}

	@Override
	public void cleanRouteFareChm() {
		System.out.println("Start cleaning routeFareChm at " + new Date());
		routeFareDtoChm.forEachKey(4, k -> {
			routeFareDtoChm.remove(k);
		});
	}

}
