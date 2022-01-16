package org.cloudevguru.hkbus.enquiry.api.manager;

import java.util.Date;

import org.cloudevguru.hkbus.enquiry.api.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemManager {
	
	@Autowired
	private SystemService systemService;

	@Scheduled(cron="${evict.cache.cron}")
	public void cacheAllEvict() {
		System.out.println(String.format("Evicting all caches at %s",new Date()));
		systemService.cacheAllEvict();
	}

	public void cacheEvict(String cacheName) {
		System.out.println(String.format("Evicting %s at %s",cacheName,new Date()));
		systemService.cacheEvict(cacheName);
	}

}
