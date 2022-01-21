package org.cloudevguru.hkbus.enquiry.api.manager;

import java.util.ArrayList;
import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant.*;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopEtaResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1StopResponse;
import org.cloudevguru.hkbus.enquiry.api.service.CTBNWFBService;
import org.cloudevguru.hkbus.enquiry.api.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CTBNWFBManager {

	@Autowired
	private CTBNWFBService ctbnwfbService;

	@Autowired
	private UtilityService utilityService;

	// v1
	// route
	public CTBNWFBv1RouteListResponse getCTBNWFBv1AllRoutesByCompany(String company) {
		return ctbnwfbService.getCTBNWFBv1AllRoutesByCompany(company.toUpperCase());
	}
	
	public CTBNWFBv1RouteListResponse getCTBNWFBv1RouteListByCompanyAndRoute(String company, String route) {
		CTBNWFBv1RouteListResponse response = new CTBNWFBv1RouteListResponse();
		List<CTBNWFBv1RouteDto> dtos = new ArrayList<CTBNWFBv1RouteDto>();
		CTBNWFBv1RouteStopListResponse ctbnwfBv1RouteStopListInboundResponse = ctbnwfbService
				.getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(company, route,
						DirectionFullEum.INBOUND.getValue());
		CTBNWFBv1RouteResponse outboundResponse = ctbnwfbService.getCTBNWFBv1RouteByCompanyAndRoute(company, route);
		CTBNWFBv1RouteDto outboundRouteDto = outboundResponse.getDto();
		if (outboundRouteDto.getCompany() != null) {
			outboundRouteDto.setBound(DirectionShortEum.OUTBOUND.getValue());
			if (ctbnwfBv1RouteStopListInboundResponse.getDtos().isEmpty()) {
				// If no inbound route-stop list, it is Circular Route and only have outbound
				dtos.add(outboundRouteDto);
			} else {
				// If have inbound route-stop list, it is not Circular Route and have both
				// direction
				dtos.add(outboundRouteDto);
				dtos.add(ctbnwfbService.convertToInbound(outboundResponse.getDto()));
			}
		}
		response.setType(RequestTypeEum.ROUTELIST.getValue());
		response.setVersion(outboundResponse.getVersion());
		response.setGeneratedTimestamp(outboundResponse.getGeneratedTimestamp());
		response.setDtos(dtos);
		return response;
	}

	public CTBNWFBv1RouteResponse getCTBNWFBv1RouteByCompanyAndRouteAndDirection(String company, String route) {
		return ctbnwfbService.getCTBNWFBv1RouteByCompanyAndRoute(company, route);
	}

	// stop
	public CTBNWFBv1StopResponse getCTBNWFBv1StopByStopId(String stopId) {
		return ctbnwfbService.getCTBNWFBv1StopByStopId(stopId);
	}

	// routestop
	public CTBNWFBv1RouteStopListResponse getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(String company,
			String route, String direction) {
		String directionFull = utilityService.convertDirectionToFull(direction);
		return ctbnwfbService.getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(company, route.toUpperCase(),
				directionFull);
	}

	public CTBNWFBv1RouteStopEtaResponse getCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRoute(String companyId,
			String stopId, String route) {
		return ctbnwfbService.getCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRoute(companyId, stopId,
				route.toUpperCase());
	}

}
