package org.cloudevguru.hkbus.enquiry.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDetailResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDto;
import org.cloudevguru.hkbus.enquiry.api.service.ConcurrentHashMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcurrentHashMapServiceImpl implements ConcurrentHashMapService {

	@Autowired
	private ConcurrentHashMap<String, List<ManagedRouteDto>> managedRouteListResponseChm;

	@Autowired
	private ConcurrentHashMap<String, ManagedRouteDetailResponse> managedRouteDetailResponseChm;

	@Override
	public List<ManagedRouteDto> getRouteListFromRouteListChmByRouteStartWith(String route) {
		List<ManagedRouteDto> dtos = new ArrayList<ManagedRouteDto>();
		managedRouteListResponseChm.forEachKey(8, k -> {
			String[] routeInfo = k.split("-");
			if (routeInfo.length > 1) {
				if (routeInfo[1].startsWith(route)) {
					ManagedRouteDto dto = managedRouteListResponseChm.get(k).get(0);
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
	public Boolean isEmptyRouteListChm() {
		return managedRouteListResponseChm.isEmpty();
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
		System.out.println("Start cleaning routeDetailChm" + new Date());
		managedRouteDetailResponseChm.forEachKey(4, k -> {
			managedRouteDetailResponseChm.remove(k);
		});
	}

}
