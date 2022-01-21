package org.cloudevguru.hkbus.enquiry.api.service;

import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopEtaResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1StopResponse;

public interface CTBNWFBService {

	// v1
	// Route
	CTBNWFBv1RouteListResponse getCTBNWFBv1AllRoutesByCompany(String company);
	
	CTBNWFBv1RouteResponse getCTBNWFBv1RouteByCompanyAndRoute(String company, String route);

	CTBNWFBv1RouteDto convertToInbound(CTBNWFBv1RouteDto originCTBNWFBv1RouteDto);

	// Stop
	CTBNWFBv1StopResponse getCTBNWFBv1StopByStopId(String stopId);

	// RouteStop
	CTBNWFBv1RouteStopListResponse getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(String company, String route,
			String direction);

	CTBNWFBv1RouteStopEtaResponse getCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRoute(String companyId, String stopId,
			String route);
}
