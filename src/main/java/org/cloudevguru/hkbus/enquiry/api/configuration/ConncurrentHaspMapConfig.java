package org.cloudevguru.hkbus.enquiry.api.configuration;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDetailResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.routefare.v1.RouteFareDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConncurrentHaspMapConfig {
	
	private ConcurrentHashMap<String, ManagedRouteDto> rawRouteConcurrentHashMap;
	private ConcurrentHashMap<String, List<ManagedRouteDto>> managedRouteListConcurrentHashMap;
	private ConcurrentHashMap<String, ManagedRouteDetailResponse> managedRouteDetailResponseConcurrentHashMap;
	private ConcurrentHashMap<String, RouteFareDto> routeFareDtoConcurrentHashMap;

	public ConncurrentHaspMapConfig() {
		rawRouteConcurrentHashMap=new ConcurrentHashMap<String,ManagedRouteDto>();
		managedRouteListConcurrentHashMap=new ConcurrentHashMap<String, List<ManagedRouteDto>>();
		managedRouteDetailResponseConcurrentHashMap=new ConcurrentHashMap<String, ManagedRouteDetailResponse>();
		routeFareDtoConcurrentHashMap=new ConcurrentHashMap<String, RouteFareDto>();
	}
	
	@Bean
	public ConcurrentHashMap<String, ManagedRouteDto> rawRouteChm() {
		return  rawRouteConcurrentHashMap;
	}
	
	@Bean
	public ConcurrentHashMap<String, List<ManagedRouteDto>> managedRouteListResponseChm() {
		return  managedRouteListConcurrentHashMap;
	}

	@Bean
	public ConcurrentHashMap<String, ManagedRouteDetailResponse> managedRouteDetailResponseChm() {
		return  managedRouteDetailResponseConcurrentHashMap;
	}
	
	@Bean
	public ConcurrentHashMap<String, RouteFareDto> routeFareDtoChm() {
		return  routeFareDtoConcurrentHashMap;
	}
}
