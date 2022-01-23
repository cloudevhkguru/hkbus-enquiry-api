package org.cloudevguru.hkbus.enquiry.api.configuration;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDetailResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConncurrentHaspMapConfig {
	
	private ConcurrentHashMap<String, List<ManagedRouteDto>> managedRouteListConcurrentHashMap;
	private ConcurrentHashMap<String, ManagedRouteDetailResponse> managedRouteDetailResponseConcurrentHashMap;

	public ConncurrentHaspMapConfig() {
		managedRouteListConcurrentHashMap=new ConcurrentHashMap<String, List<ManagedRouteDto>>();
		managedRouteDetailResponseConcurrentHashMap=new ConcurrentHashMap<String, ManagedRouteDetailResponse>();
	}
	
	@Bean
	public ConcurrentHashMap<String, List<ManagedRouteDto>> managedRouteListResponseChm() {
		return  managedRouteListConcurrentHashMap;
	}

	@Bean
	public ConcurrentHashMap<String, ManagedRouteDetailResponse> managedRouteDetailResponseChm() {
		return  managedRouteDetailResponseConcurrentHashMap;
	}
}
