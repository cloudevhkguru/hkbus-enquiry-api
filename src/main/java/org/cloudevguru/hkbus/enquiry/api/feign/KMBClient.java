package org.cloudevguru.hkbus.enquiry.api.feign;

import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "kmbClient", url = "https://data.etabus.gov.hk")
public interface KMBClient {

	// v1
	// Route
	@RequestMapping(method = RequestMethod.GET, value = "/v1/transport/kmb/route")
	KMBv1RouteListResponse getKMBv1RouteList();

	@RequestMapping(method = RequestMethod.GET, value = "/v1/transport/kmb/route/{route}/{direction}/{serviceType}")
	KMBv1RouteResponse getKMBv1Route(@PathVariable("route") String route, @PathVariable("direction") String direction,
			@PathVariable("serviceType") String serviceType);

	// Stop
	@RequestMapping(method = RequestMethod.GET, value = "/v1/transport/kmb/stop")
	KMBv1StopListResponse getKMBv1StopList();

	@RequestMapping(method = RequestMethod.GET, value = "/v1/transport/kmb/stop/{stopId}")
	KMBv1StopResponse getKMBv1Stop(@PathVariable("stopId") String stopId);

	//RouteStop
	@RequestMapping(method = RequestMethod.GET, value = "/v1/transport/kmb/route-stop")
	KMBv1RouteStopListResponse getKMBv1RouteStopList();
	
	@RequestMapping(method = RequestMethod.GET, value = "/v1/transport/kmb/route-stop/{route}/{direction}/{serviceType}")
	KMBv1RouteStopListResponse getKMBv1RouteStopList(@PathVariable("route") String route, @PathVariable("direction") String direction,
			@PathVariable("serviceType") String serviceType);

}
