package org.cloudevguru.hkbus.enquiry.api.service;

public interface UtilityService {

	String convertDirectionToFull(String direction) throws IllegalArgumentException;
	
	void checkIsValidBusCompany(String company) throws IllegalArgumentException;

}
