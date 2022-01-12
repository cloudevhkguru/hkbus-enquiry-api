package org.cloudevguru.hkbus.enquiry.api.manager;

import java.util.ArrayList;
import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant.*;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopEtaResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopResponse;
import org.cloudevguru.hkbus.enquiry.api.service.KMBService;
import org.cloudevguru.hkbus.enquiry.api.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KMBManager {

	@Autowired
	private KMBService kmbService;

	@Autowired
	private UtilityService utilityService;

	// v1
	// route
	public KMBv1RouteListResponse getKMBv1RouteList() {
		return kmbService.getKMBv1RouteList();
	}

	public KMBv1RouteListResponse getKMBv1RouteListByRoute(String route) {
		KMBv1RouteResponse kmbResponse = new KMBv1RouteResponse();
		KMBv1RouteListResponse response = new KMBv1RouteListResponse();
		List<KMBv1RouteDto> dtos = new ArrayList<KMBv1RouteDto>();
		for (DirectionFullEum directionFull : DirectionFullEum.values()) {
			kmbResponse = kmbService.getKMBv1RouteByRouteAndDirectionAndServiceType(route.toUpperCase(),
					directionFull.getValue(), "1");
			if (kmbResponse.getDto().getOriginEn() != null) {
				dtos.add(kmbResponse.getDto());
			}
		}
		response.setType(RequestTypeEum.ROUTELIST.getValue());
		response.setVersion(kmbResponse.getVersion());
		response.setGeneratedTimestamp(kmbResponse.getGeneratedTimestamp());
		response.setDtos(dtos);
		return response;
	}

	public KMBv1RouteResponse getKMBv1RouteByRouteAndDirection(String route, String direction) {
		String directionFull = utilityService.convertDirectionToFull(direction);
		return kmbService.getKMBv1RouteByRouteAndDirectionAndServiceType(route.toUpperCase(), directionFull, "1");
	}

	// stop
	public KMBv1StopListResponse getKMBv1StopList() {
		return kmbService.getKMBv1StopList();
	}

	public KMBv1StopResponse getKMBv1StopByStopId(String stopId) {
		return kmbService.getKMBv1StopByStopId(stopId);
	}

	// routestop
	public KMBv1RouteStopListResponse getKMBv1RouteStopList() {
		return kmbService.getKMBv1RouteStopList();
	}

	public KMBv1RouteStopListResponse getKMBv1RouteStopListByRouteAndDirection(String route, String direction) {
		String directionFull = utilityService.convertDirectionToFull(direction);
		return kmbService.getKMBv1RouteStopListByRouteAndDirectionAndServiceType(route.toUpperCase(), directionFull,
				"1");
	}

	public KMBv1RouteStopEtaResponse getKMBv1RouteStopEtaByRouteAndStopId(String stopId, String route) {
		return kmbService.getKMBv1RouteStopEtaByStopIdAndRouteAndServiceType(stopId, route.toUpperCase(), "1");
	}

}
