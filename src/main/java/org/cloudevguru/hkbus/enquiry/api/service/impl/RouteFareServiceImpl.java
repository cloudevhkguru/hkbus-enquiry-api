package org.cloudevguru.hkbus.enquiry.api.service.impl;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.dto.routefare.v1.RouteFareDto;
import org.cloudevguru.hkbus.enquiry.api.feign.RouteFareClient;
import org.cloudevguru.hkbus.enquiry.api.service.RouteFareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteFareServiceImpl implements RouteFareService{

	@Autowired
	private RouteFareClient routeFareClient;
	
	@Override
	public List<RouteFareDto> getAllBusesRouteFare(){
		return routeFareClient.getAllBusesRouteFare();
	}
}
