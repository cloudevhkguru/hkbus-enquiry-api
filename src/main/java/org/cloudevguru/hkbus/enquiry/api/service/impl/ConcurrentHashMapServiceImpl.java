package org.cloudevguru.hkbus.enquiry.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDto;
import org.cloudevguru.hkbus.enquiry.api.service.ConcurrentHashMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcurrentHashMapServiceImpl implements ConcurrentHashMapService {

	@Autowired
	private ConcurrentHashMap<String, List<ManagedRouteDto>> managedRouteListResponseChm;

	@Override
	public List<ManagedRouteDto> getRouteListFromRouteListChmByRoute(String route) {
		List<ManagedRouteDto> dtos = new ArrayList<ManagedRouteDto>();
		managedRouteListResponseChm.forEachKey(8, k -> {
			String[] routeInfo = k.split("-");
			if(routeInfo.length>1) {
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
		System.out.println("Creating record with key=" + routeKey + " at " + new Date());
		return managedRouteListResponseChm.putIfAbsent(routeKey, dtos);
	}
	
	@Override
	public Boolean isEmptyRouteListChm() {
		return managedRouteListResponseChm.isEmpty();
	}

	@Override
	public void cleanRouteListChm() {
		managedRouteListResponseChm.forEachKey(4, k -> {
			managedRouteListResponseChm.remove(k);
		});
	}

}
