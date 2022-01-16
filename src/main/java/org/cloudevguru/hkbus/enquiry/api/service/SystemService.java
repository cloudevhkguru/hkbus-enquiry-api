package org.cloudevguru.hkbus.enquiry.api.service;

public interface SystemService {

	void cacheEvict(String cacheName);

	void cacheAllEvict();
}
