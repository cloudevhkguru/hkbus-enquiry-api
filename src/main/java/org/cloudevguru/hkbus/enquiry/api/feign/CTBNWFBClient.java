package org.cloudevguru.hkbus.enquiry.api.feign;

import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1StopResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ctbnfwbClient", url = "${ctbnwfb.api.url}")
public interface CTBNWFBClient {

	// v1
	// Route
	@RequestMapping(method = RequestMethod.GET, value = "/v1.1/transport/citybus-nwfb/route/{company}/{route}")
	CTBNWFBv1RouteResponse getCTBNWFBv1Route(@PathVariable("company") String company,@PathVariable("route") String route);

	// Stop
	@RequestMapping(method = RequestMethod.GET, value = "/v1.1/transport/citybus-nwfb/stop/{stopId}")
	CTBNWFBv1StopResponse getCTBNWFBv1Stop(@PathVariable("stopId") String stopId);

	//RouteStop	
	@RequestMapping(method = RequestMethod.GET, value = "/v1.1/transport/citybus-nwfb/route-stop/{company}/{route}/{direction}")
	CTBNWFBv1RouteStopListResponse getCTBNWFBv1RouteStopList(@PathVariable("company") String serviceType,@PathVariable("route") String route, @PathVariable("direction") String direction);

}
