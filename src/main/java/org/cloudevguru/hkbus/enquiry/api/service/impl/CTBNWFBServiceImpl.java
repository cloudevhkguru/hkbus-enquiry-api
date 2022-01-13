package org.cloudevguru.hkbus.enquiry.api.service.impl;

import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant.*;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopEtaResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1StopResponse;
import org.cloudevguru.hkbus.enquiry.api.feign.CTBNWFBClient;
import org.cloudevguru.hkbus.enquiry.api.service.CTBNWFBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CTBNWFBServiceImpl implements CTBNWFBService {
	
	@Autowired
	private CTBNWFBClient ctbnwfbClient;

	// v1
	// Route
	@Override
	public CTBNWFBv1RouteResponse getCTBNWFBv1RouteByCompanyAndRoute(String company,String route) {
		return ctbnwfbClient.getCTBNWFBv1Route(company,route);
	}

	// stop	
	@Override
	public CTBNWFBv1StopResponse getCTBNWFBv1StopByStopId(String stopId) {
		return ctbnwfbClient.getCTBNWFBv1Stop(stopId.toUpperCase());
	}

	//RouteStop	
	@Override
	public CTBNWFBv1RouteStopListResponse getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(String company,String route, String direction) {
		return ctbnwfbClient.getCTBNWFBv1RouteStopList(company, route, direction);
	}
	
	//RouteStopETA
	@Override
	public CTBNWFBv1RouteStopEtaResponse getCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRoute(String companyId, String stopId,
			String route) {
		return ctbnwfbClient.getCTBNWFBv1RouteStopEta(companyId, stopId, route);
	}
	
	//Utility
	public CTBNWFBv1RouteDto convertToInbound(CTBNWFBv1RouteDto originCTBNWFBv1RouteDto) {
		CTBNWFBv1RouteDto inboundRouteDto=new CTBNWFBv1RouteDto();
		inboundRouteDto.setCompany(originCTBNWFBv1RouteDto.getCompany());
		inboundRouteDto.setRoute(originCTBNWFBv1RouteDto.getRoute());
		inboundRouteDto.setBound(DirectionShortEum.INBOUND.getValue());
		inboundRouteDto.setOriginEn(originCTBNWFBv1RouteDto.getDestinationEn());
		inboundRouteDto.setOriginTc(originCTBNWFBv1RouteDto.getDestinationTc());
		inboundRouteDto.setOriginSc(originCTBNWFBv1RouteDto.getDestinationSc());
		inboundRouteDto.setDestinationEn(originCTBNWFBv1RouteDto.getOriginEn());
		inboundRouteDto.setDestinationTc(originCTBNWFBv1RouteDto.getOriginTc());
		inboundRouteDto.setDestinationSc(originCTBNWFBv1RouteDto.getOriginSc());
		return inboundRouteDto;

	}

}
