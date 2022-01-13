package org.cloudevguru.hkbus.enquiry.api.service;

import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopEtaResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopResponse;

public interface KMBService {

	// v1
	// Route
	KMBv1RouteListResponse getKMBv1RouteList();

	KMBv1RouteResponse getKMBv1RouteByRouteAndDirectionAndServiceType(String route, String direction,
			String serviceType);

	// Stop
	KMBv1StopListResponse getKMBv1StopList();

	KMBv1StopResponse getKMBv1StopByStopId(String stopId);

	// RouteStop
	KMBv1RouteStopListResponse getKMBv1RouteStopList();

	KMBv1RouteStopListResponse getKMBv1RouteStopListByRouteAndDirectionAndServiceType(String route, String direction,
			String serviceType);

	KMBv1RouteStopEtaResponse getKMBv1RouteStopEtaByStopIdAndRouteAndServiceType(String stopId, String route,
			String serviceType);

}