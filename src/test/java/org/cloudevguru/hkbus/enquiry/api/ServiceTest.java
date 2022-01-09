package org.cloudevguru.hkbus.enquiry.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.configuration.UnitTestConfiguration;
import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant.*;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopResponse;
import org.cloudevguru.hkbus.enquiry.api.service.KMBService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@SpringBootTest
@Import(UnitTestConfiguration.class)
public class ServiceTest {

	@Autowired
	private KMBService kmbService;

	//KMB
	@Test
	@DisplayName("KMBService.getKMBv1RouteByRouteAndDirectionAndServiceType Test KMB")
	public void testGetKMBv1RouteByRouteAndDirectionAndServiceType() throws Exception {
		KMBv1RouteResponse response = kmbService.getKMBv1RouteByRouteAndDirectionAndServiceType(
				UnitTestConfiguration.testKMBRoute1A(), DirectionFullEum.OUTBOUND.getValue(), "1");
		KMBv1RouteDto dto = response.getDto();
		assertThat(dto.getBound()).isEqualTo(DirectionShortEum.OUTBOUND.getValue());
		assertThat(dto.getOriginEn()).isNotEmpty();
	}
	
	@Test
	@DisplayName("KMBService.getKMBv1StopByStopId Test KMB")
	public void testGetKMBv1StopByStopId() throws Exception {
		KMBv1StopResponse response = kmbService.getKMBv1StopByStopId(UnitTestConfiguration.testKMBStop());
		KMBv1StopDto dto = response.getDto();
		assertThat(dto.getStop()).isEqualTo(UnitTestConfiguration.testKMBStop());
		assertThat(dto.getLongtitude()).isNotNull();
	}
	

	@Test
	@DisplayName("KMBService.getKMBv1RouteStopListByRouteAndDirectionAndServiceType Test KMB")
	public void testGetKMBv1RouteStopListByRouteAndDirectionAndServiceType() throws Exception {
		KMBv1RouteStopListResponse response = kmbService.getKMBv1RouteStopListByRouteAndDirectionAndServiceType(
				UnitTestConfiguration.testKMBRoute101(), DirectionFullEum.INBOUND.getValue(), "1");
		List<KMBv1RouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testKMBRoute101());
	}

}
