package org.cloudevguru.hkbus.enquiry.api.service.impl;

import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopResponse;
import org.cloudevguru.hkbus.enquiry.api.feign.KMBClient;
import org.cloudevguru.hkbus.enquiry.api.service.KMBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class KMBServiceImpl implements KMBService {

	@Autowired
	private KMBClient kmbClient;

	// v1
	// Route
	@Override
	public KMBv1RouteListResponse getKMBv1RouteList() {
		return kmbClient.getKMBv1RouteList();
	}

	@Override
	public KMBv1RouteResponse getKMBv1RouteByRouteAndDirectionAndServiceType(String route, String direction,
			String serviceType) {
		return kmbClient.getKMBv1Route(route, direction, serviceType);
	}

	// stop
	@Override
	public KMBv1StopListResponse getKMBv1StopList() {
		return kmbClient.getKMBv1StopList();
	}
	
	@Override
	public KMBv1StopResponse getKMBv1StopByStopId(String stopId) {
		return kmbClient.getKMBv1Stop(stopId.toUpperCase());
	}

	//RouteStop
	@Override
	public KMBv1RouteStopListResponse getKMBv1RouteStopList() {
		return kmbClient.getKMBv1RouteStopList();
	}
	
	@Override
	public KMBv1RouteStopListResponse getKMBv1RouteStopListByRouteAndDirectionAndServiceType(String route, String direction,
			String serviceType) {
		return kmbClient.getKMBv1RouteStopList(route, direction, serviceType);
	}

}
