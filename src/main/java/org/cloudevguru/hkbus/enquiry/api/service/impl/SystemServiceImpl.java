package org.cloudevguru.hkbus.enquiry.api.service.impl;

import org.cloudevguru.hkbus.enquiry.api.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {
	@Autowired
	private CacheManager cacheManager;

	@Override
	public void cacheEvict(String cacheName) {
		cacheManager.getCache(cacheName).clear();
	}

	@Override
	public void cacheAllEvict() {
		cacheManager.getCacheNames().stream().forEach(cacheName -> {
			cacheManager.getCache(cacheName).clear();
		});
	}
}
