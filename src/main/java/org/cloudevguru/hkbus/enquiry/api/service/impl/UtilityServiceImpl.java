package org.cloudevguru.hkbus.enquiry.api.service.impl;

import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant.*;
import org.cloudevguru.hkbus.enquiry.api.service.UtilityService;
import org.springframework.stereotype.Service;

@Service
public class UtilityServiceImpl implements UtilityService{
	
	@Override
	public String convertDirectionToFull(String direction) throws IllegalArgumentException {
		if(direction.equalsIgnoreCase(DirectionShortEum.INBOUND.getValue())||direction.equalsIgnoreCase(DirectionFullEum.INBOUND.getValue())) {
			return DirectionFullEum.INBOUND.getValue();
		}else if(direction.equalsIgnoreCase(DirectionShortEum.OUTBOUND.getValue())||direction.equalsIgnoreCase(DirectionFullEum.OUTBOUND.getValue())){
			return DirectionFullEum.OUTBOUND.getValue();
		}else {
			throw new IllegalArgumentException("Direction should be 'o','i','inbound' or 'outbound'");
		}
	}
	
	@Override
	public void checkIsValidBusCompany(String company) throws IllegalArgumentException{
		for(BusCompanyEum busCompany: BusCompanyEum.values()) {
			if(company.equalsIgnoreCase(busCompany.getValue())) {
				return;
			}
		}
		throw new IllegalArgumentException("Company should be 'kmb','ctb','nwfb'");
	}

}
