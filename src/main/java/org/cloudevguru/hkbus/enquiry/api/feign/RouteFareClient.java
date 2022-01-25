package org.cloudevguru.hkbus.enquiry.api.feign;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.dto.routefare.v1.RouteFareDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "routeFareClient", url = "${routefare.api.url}")
public interface RouteFareClient {

	@RequestMapping(method = RequestMethod.GET, value = "/td/routes-fares-geojson/JSON_BUS.json")
	List<RouteFareDto> getAllBusesRouteFare();
}
