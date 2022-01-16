package org.cloudevguru.hkbus.enquiry.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.configuration.UnitTestConfiguration;
import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant.*;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1StopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1StopResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopResponse;
import org.cloudevguru.hkbus.enquiry.api.service.CTBNWFBService;
import org.cloudevguru.hkbus.enquiry.api.service.KMBService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@SpringBootTest
@Import(UnitTestConfiguration.class)
public class ServiceTest extends BaseTestEntity {

	@Autowired
	private KMBService kmbService;
	
	@Autowired
	private CTBNWFBService ctbnwfbService;

	// KMB
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

	// CTB
	@Test
	@DisplayName("CTBNWFBService.getCTBNWFBv1RouteByCompanyAndRoute Test CTB")
	public void testGetCTBNWFBv1RouteByCompanyAndRouteCTB() throws Exception {
		CTBNWFBv1RouteResponse response = ctbnwfbService.getCTBNWFBv1RouteByCompanyAndRoute(
				BusCompanyEum.CTB.getValue(), UnitTestConfiguration.testCTBRoute671());
		CTBNWFBv1RouteDto dto = response.getDto();
		assertThat(dto.getRoute()).isEqualTo(UnitTestConfiguration.testCTBRoute671());
		assertThat(dto.getOriginEn()).isNotEmpty();
	}

	@Test
	@DisplayName("CTBNWFBService.getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection Test CTB")
	public void testgetCTBNWFBv1RouteStopListByCompanyAndRouteAndDirectionCTB() throws Exception {
		CTBNWFBv1RouteStopListResponse response = ctbnwfbService.getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(
				BusCompanyEum.CTB.getValue(), UnitTestConfiguration.testCTBRouteA29(),
				DirectionFullEum.INBOUND.getValue());
		List<CTBNWFBv1RouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testCTBRouteA29());
	}

	// NWFB
	@Test
	@DisplayName("CTBNWFBService.getCTBNWFBv1RouteByCompanyAndRoute Test NWFB")
	public void testGetCTBNWFBv1RouteByCompanyAndRouteNWFB() throws Exception {
		CTBNWFBv1RouteResponse response = ctbnwfbService.getCTBNWFBv1RouteByCompanyAndRoute(
				BusCompanyEum.NWFB.getValue(), UnitTestConfiguration.testNWFBRoute15C());
		CTBNWFBv1RouteDto dto = response.getDto();
		assertThat(dto.getRoute()).isEqualTo(UnitTestConfiguration.testNWFBRoute15C());
		assertThat(dto.getOriginEn()).isNotEmpty();
	}

	@Test
	@DisplayName("CTBNWFBService.getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection Test NWFB")
	public void testgetCTBNWFBv1RouteStopListByCompanyAndRouteAndDirectionNWFB() throws Exception {
		CTBNWFBv1RouteStopListResponse response = ctbnwfbService.getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(
				BusCompanyEum.NWFB.getValue(), UnitTestConfiguration.testNWFBRoute18(),
				DirectionFullEum.OUTBOUND.getValue());
		List<CTBNWFBv1RouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testNWFBRoute18());
	}

	@Test
	@DisplayName("CTBNWFBService.getCTBNWFBv1StopByStopId Test")
	public void testGetCTBNWFBv1StopByStopId() throws Exception {
		CTBNWFBv1StopResponse response = ctbnwfbService
				.getCTBNWFBv1StopByStopId(UnitTestConfiguration.testCTBNWFBStop());
		CTBNWFBv1StopDto dto = response.getDto();
		assertThat(dto.getStop()).isEqualTo(UnitTestConfiguration.testCTBNWFBStop());
		assertThat(dto.getLongtitude()).isNotNull();
	}

}
