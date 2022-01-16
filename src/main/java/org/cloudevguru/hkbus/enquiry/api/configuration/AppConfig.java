package org.cloudevguru.hkbus.enquiry.api.configuration;

import org.cloudevguru.hkbus.enquiry.api.constants.CacheConstant;
import org.modelmapper.ModelMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager(CacheConstant.getCaches());
	}


}
