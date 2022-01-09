package org.cloudevguru.hkbus.enquiry.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.configuration.UnitTestConfiguration;
import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant.*;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.manager.CTBNWFBManager;
import org.cloudevguru.hkbus.enquiry.api.manager.KMBManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(UnitTestConfiguration.class)
public class ManagerTest {

	@Autowired
	private KMBManager kmbManager;

	@Autowired
	private CTBNWFBManager ctbnwfbManager;

	// KMB
	@Test
	@DisplayName("KMBManager.getKMBv1RouteByRouteAndDirection Test KMB")
	public void testGetKMBv1RouteByRouteAndDirection() throws Exception {
		KMBv1RouteResponse response = kmbManager.getKMBv1RouteByRouteAndDirection(
				UnitTestConfiguration.testKMBRoute1A().toLowerCase(), DirectionShortEum.INBOUND.getValue());
		KMBv1RouteDto dto = response.getDto();
		assertThat(dto.getBound()).isEqualTo(DirectionShortEum.INBOUND.getValue());
		assertThat(dto.getOriginEn()).isNotEmpty();
	}

	@Test
	@DisplayName("KMBManager.getKMBv1RouteStopListByRouteAndDirection Test KMB")
	public void testGetKMBv1RouteStopListByRouteAndDirection() throws Exception {
		KMBv1RouteStopListResponse response = kmbManager.getKMBv1RouteStopListByRouteAndDirection(
				UnitTestConfiguration.testKMBRoute101(), DirectionShortEum.OUTBOUND.getValue());
		List<KMBv1RouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testKMBRoute101());
		assertThat(dtos.get(2).getStop()).isNotBlank();
	}

	// CTB
	@Test
	@DisplayName("CTBNWFBManager.getCTBNWFBv1RouteByCompanyAndRouteAndDirection Test CTB")
	public void testGetCTBNWFBv1RouteByCompanyAndRouteAndDirectionCTB() throws Exception {
		CTBNWFBv1RouteResponse response = ctbnwfbManager.getCTBNWFBv1RouteByCompanyAndRouteAndDirection(
				BusCompanyEum.CTB.getValue(), UnitTestConfiguration.testCTBRoute671().toLowerCase());
		CTBNWFBv1RouteDto dto = response.getDto();
		assertThat(dto.getRoute()).isEqualTo(UnitTestConfiguration.testCTBRoute671().toLowerCase());
		assertThat(dto.getOriginEn()).isNotEmpty();
	}

	@Test
	@DisplayName("CTBNWFBManager.getCTBNWFBv1RouteListByCompanyAndRoute Test CTB")
	public void testGetCTBNWFBv1RouteListByCompanyAndRouteCTB() throws Exception {
		CTBNWFBv1RouteListResponse response = ctbnwfbManager.getCTBNWFBv1RouteListByCompanyAndRoute(
				BusCompanyEum.CTB.getValue(), UnitTestConfiguration.testCTBRouteA29().toLowerCase());
		List<CTBNWFBv1RouteDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testCTBRouteA29());
	}

	@Test
	@DisplayName("CTBNWFBManager.getKMBv1RouteStopListByRouteAndDirection Test CTB")
	public void testGetCTBNWFBv1RouteStopListByCompanyAndRouteAndDirectionCTB() throws Exception {
		CTBNWFBv1RouteStopListResponse response = ctbnwfbManager.getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(
				BusCompanyEum.CTB.getValue(), UnitTestConfiguration.testCTBRouteA29(),
				DirectionShortEum.OUTBOUND.getValue());
		List<CTBNWFBv1RouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testCTBRouteA29());
		assertThat(dtos.get(2).getStop()).isNotBlank();
	}

	// NWFB
	@Test
	@DisplayName("CTBNWFBManager.getCTBNWFBv1RouteByCompanyAndRouteAndDirection Test NWFB")
	public void testGetCTBNWFBv1RouteByCompanyAndRouteAndDirectionNWFB() throws Exception {
		CTBNWFBv1RouteResponse response = ctbnwfbManager.getCTBNWFBv1RouteByCompanyAndRouteAndDirection(
				BusCompanyEum.NWFB.getValue(), UnitTestConfiguration.testNWFBRoute18().toLowerCase());
		CTBNWFBv1RouteDto dto = response.getDto();
		assertThat(dto.getRoute()).isEqualTo(UnitTestConfiguration.testNWFBRoute18().toLowerCase());
		assertThat(dto.getOriginEn()).isNotEmpty();
	}

	@Test
	@DisplayName("CTBNWFBManager.getCTBNWFBv1RouteListByCompanyAndRoute Test NWFB")
	public void testGetCTBNWFBv1RouteListByCompanyAndRouteNWFB() throws Exception {
		CTBNWFBv1RouteListResponse response = ctbnwfbManager.getCTBNWFBv1RouteListByCompanyAndRoute(
				BusCompanyEum.NWFB.getValue(), UnitTestConfiguration.testNWFBRoute18().toLowerCase());
		List<CTBNWFBv1RouteDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testNWFBRoute18());
	}

	@Test
	@DisplayName("CTBNWFBManager.getKMBv1RouteStopListByRouteAndDirection Test NWFB")
	public void testGetCTBNWFBv1RouteStopListByCompanyAndRouteAndDirectionNWFB() throws Exception {
		CTBNWFBv1RouteStopListResponse response = ctbnwfbManager.getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(
				BusCompanyEum.NWFB.getValue(), UnitTestConfiguration.testNWFBRoute15C(),
				DirectionShortEum.OUTBOUND.getValue());
		List<CTBNWFBv1RouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testNWFBRoute15C());
		assertThat(dtos.get(2).getStop()).isNotBlank();
	}

}
