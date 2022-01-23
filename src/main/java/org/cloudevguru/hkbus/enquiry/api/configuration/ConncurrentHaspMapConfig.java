package org.cloudevguru.hkbus.enquiry.api.configuration;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConncurrentHaspMapConfig {
	
	private ConcurrentHashMap<String, List<ManagedRouteDto>> managedRouteListConcurrentHashMap;

	public ConncurrentHaspMapConfig() {
		managedRouteListConcurrentHashMap=new ConcurrentHashMap<String, List<ManagedRouteDto>>();
	}
	
	@Bean
	public ConcurrentHashMap<String, List<ManagedRouteDto>> managedRouteListResponseChm() {
		return  managedRouteListConcurrentHashMap;
	}

}
