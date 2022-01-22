package org.cloudevguru.hkbus.enquiry.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.configuration.UnitTestConfiguration;
import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant.*;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopEtaDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopEtaResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopEtaDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopEtaResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDetailDto;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDetailResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteStopEtaDto;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteStopEtaResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedRouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedStopDetailDto;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.managed.ManagedStopResponse;
import org.cloudevguru.hkbus.enquiry.api.manager.CTBNWFBManager;
import org.cloudevguru.hkbus.enquiry.api.manager.KMBManager;
import org.cloudevguru.hkbus.enquiry.api.manager.ManagedManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(UnitTestConfiguration.class)
public class ManagerTest extends BaseTestEntity {

	@Autowired
	private KMBManager kmbManager;

	@Autowired
	private CTBNWFBManager ctbnwfbManager;

	@Autowired
	private ManagedManager managedManager;

	// KMB
	@Test
	@DisplayName("KMBManager.getKMBv1RouteByRouteAndDirectionAndServiceType Test KMB")
	public void testgetKMBv1RouteByRouteAndDirectionAndServiceType() throws Exception {
		KMBv1RouteResponse response = kmbManager.getKMBv1RouteByRouteAndDirectionAndServiceType(
				UnitTestConfiguration.testKMBRoute1A().toLowerCase(), DirectionShortEum.INBOUND.getValue(), "1");
		KMBv1RouteDto dto = response.getDto();
		assertThat(dto.getBound()).isEqualTo(DirectionShortEum.INBOUND.getValue());
		assertThat(dto.getOriginEn()).isNotEmpty();
	}

	@Test
	@DisplayName("KMBManager.getKMBv1RouteStopListByRouteAndDirectionAndServiceType Test KMB")
	public void testgetKMBv1RouteStopListByRouteAndDirectionAndServiceType() throws Exception {
		KMBv1RouteStopListResponse response = kmbManager.getKMBv1RouteStopListByRouteAndDirectionAndServiceType(
				UnitTestConfiguration.testKMBRoute101(), DirectionShortEum.OUTBOUND.getValue(), "1");
		List<KMBv1RouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testKMBRoute101());
		assertThat(dtos.get(2).getStop()).isNotBlank();
	}

	@Test
	@DisplayName("KMBManager.getKMBv1RouteStopEtaByRouteAndStopId Test KMB")
	public void testGetKMBv1RouteStopEtaByRouteAndStopId() throws Exception {
		KMBv1RouteStopEtaResponse response = kmbManager.getKMBv1RouteStopEtaByRouteAndStopIdAndServiceType(
				UnitTestConfiguration.testKMB1AInboundStop(), UnitTestConfiguration.testKMBRoute1A(), "1");
		List<KMBv1RouteStopEtaDto> dtos = response.getDtos();
		// assertThat(dtos.size()).isGreaterThan(0);
		// assertThat(dtos.get(0).getRoute()).isEqualTo(UnitTestConfiguration.testKMBRoute1A());
		// assertThat(dtos.get(0).getDestinationEn()).isNotBlank();
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
	@DisplayName("CTBNWFBManager.getKMBv1RouteStopListByRouteAndDirectionAndServiceType Test CTB")
	public void testGetCTBNWFBv1RouteStopListByCompanyAndRouteAndDirectionCTB() throws Exception {
		CTBNWFBv1RouteStopListResponse response = ctbnwfbManager.getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(
				BusCompanyEum.CTB.getValue(), UnitTestConfiguration.testCTBRouteA29(),
				DirectionShortEum.OUTBOUND.getValue());
		List<CTBNWFBv1RouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testCTBRouteA29());
		assertThat(dtos.get(2).getStop()).isNotBlank();
	}

	@Test
	@DisplayName("CTBNWFBManager.getCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRoute Test CTB")
	public void testGetCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRouteCTB() throws Exception {
		CTBNWFBv1RouteStopEtaResponse response = ctbnwfbManager.getCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRoute(
				BusCompanyEum.CTB.getValue(), UnitTestConfiguration.testCTBA29InboundStop(),
				UnitTestConfiguration.testCTBRouteA29());
		List<CTBNWFBv1RouteStopEtaDto> dtos = response.getDtos();
		// assertThat(dtos.size()).isGreaterThan(0);
		// assertThat(dtos.get(0).getRoute()).isEqualTo(UnitTestConfiguration.testCTBRouteA29());
		// assertThat(dtos.get(0).getDestinationEn()).isNotBlank();
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
	@DisplayName("CTBNWFBManager.getKMBv1RouteStopListByRouteAndDirectionAndServiceType Test NWFB")
	public void testGetCTBNWFBv1RouteStopListByCompanyAndRouteAndDirectionNWFB() throws Exception {
		CTBNWFBv1RouteStopListResponse response = ctbnwfbManager.getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(
				BusCompanyEum.NWFB.getValue(), UnitTestConfiguration.testNWFBRoute15C(),
				DirectionShortEum.OUTBOUND.getValue());
		List<CTBNWFBv1RouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testNWFBRoute15C());
		assertThat(dtos.get(2).getStop()).isNotBlank();
	}

	@Test
	@DisplayName("CTBNWFBManager.getCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRoute Test NWFB")
	public void testGetCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRouteNWFB() throws Exception {
		CTBNWFBv1RouteStopEtaResponse response = ctbnwfbManager.getCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRoute(
				BusCompanyEum.NWFB.getValue(), UnitTestConfiguration.testNWFB18OutboundStop(),
				UnitTestConfiguration.testNWFBRoute18());
		List<CTBNWFBv1RouteStopEtaDto> dtos = response.getDtos();
		// assertThat(dtos.size()).isGreaterThan(0);
		// assertThat(dtos.get(0).getRoute()).isEqualTo(UnitTestConfiguration.testNWFBRoute18());
		// assertThat(dtos.get(0).getDestinationEn()).isNotBlank();
	}

	// Managed
	// KMB
	@Test
	@DisplayName("ManagedManager.getRouteListByCompanyAndRouteAndServiceType Test KMB")
	public void testGetRouteListByCompanyAndRouteKMB() throws Exception {
		ManagedRouteListResponse response = managedManager.getRouteListByCompanyAndRouteAndServiceType(
				BusCompanyEum.KMB.getValue(), UnitTestConfiguration.testKMBRoute101(), "1");
		List<ManagedRouteDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testKMBRoute101());
	}

	@Test
	@DisplayName("ManagedManager.getRouteByCompanyAndRouteAndDirectionAndServiceType Test KMB")
	public void testGetRouteByCompanyAndRouteAndDirectionKMB() throws Exception {
		ManagedRouteResponse response = managedManager.getRouteByCompanyAndRouteAndDirectionAndServiceType(
				BusCompanyEum.KMB.getValue(), UnitTestConfiguration.testKMBRoute1A(),
				DirectionShortEum.INBOUND.getValue(), "1");
		ManagedRouteDto dto = response.getDto();
		assertThat(dto.getOriginEn()).isNotBlank();
		assertThat(dto.getRoute()).isEqualTo(UnitTestConfiguration.testKMBRoute1A());
	}

	@Test
	@DisplayName("ManagedManager.getStopByCompanyAndStopId Test KMB")
	public void testGetStopByCompanyAndStopIdKMB() throws Exception {
		ManagedStopResponse response = managedManager.getStopByCompanyAndStopId(BusCompanyEum.KMB.getValue(),
				UnitTestConfiguration.testKMBStop());
		ManagedStopDto dto = response.getDto();
		assertThat(dto.getStop()).isEqualTo(UnitTestConfiguration.testKMBStop());
		assertThat(dto.getNameEn()).isNotEmpty();
	}

	@Test
	@DisplayName("ManagedManager.getRouteStopListByCompanyAndRouteAndDirectionAndServiceType Test")
	public void testGetRouteStopListByCompanyAndRouteAndDirectionKMB() throws Exception {
		ManagedRouteStopListResponse response = managedManager
				.getRouteStopListByCompanyAndRouteAndDirectionAndServiceType(BusCompanyEum.KMB.getValue(),
						UnitTestConfiguration.testKMBRoute101(), DirectionShortEum.OUTBOUND.getValue(), "1");
		List<ManagedRouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testKMBRoute101());
		assertThat(dtos.get(2).getStop()).isNotBlank();
	}

	@Test
	@DisplayName("ManagedManager.getRouteDetailByCompanyAndRouteAndDirectionAndServiceType Test KMB")
	public void testGetRouteDetailByCompanyAndRouteAndDirectionKMB() throws Exception {
		ManagedRouteDetailResponse response = managedManager.getRouteDetailByCompanyAndRouteAndDirectionAndServiceType(
				BusCompanyEum.KMB.getValue(), UnitTestConfiguration.testKMBRoute101(),
				DirectionShortEum.OUTBOUND.getValue(), "1");
		ManagedRouteDetailDto dto = response.getDto();
		List<ManagedStopDetailDto> stopDetailDtos = dto.getStopDetailDtos();
		assertThat(dto.getBound()).isEqualToIgnoringCase(DirectionShortEum.OUTBOUND.getValue());
		assertThat(!dto.getIsCircularRoute());
		assertThat(stopDetailDtos.size()).isGreaterThan(2);
		assertThat(stopDetailDtos.get(2).getSeq()).isNotNull();
		assertThat(stopDetailDtos.get(3).getStopDto().getNameEn()).isNotNull();
	}

	@Test
	@DisplayName("ManagedManager.getRouteDetailByCompanyAndRouteAndDirectionAndServiceType Test KMB CircularRoute")
	public void testGetRouteDetailByCompanyAndRouteAndDirectionKMBCircularRoute() throws Exception {
		ManagedRouteDetailResponse response = managedManager.getRouteDetailByCompanyAndRouteAndDirectionAndServiceType(
				BusCompanyEum.KMB.getValue(), UnitTestConfiguration.testKMBCircularRoute27(),
				DirectionShortEum.INBOUND.getValue(), "1");
		ManagedRouteDetailDto dto = response.getDto();
		List<ManagedStopDetailDto> stopDetailDtos = dto.getStopDetailDtos();
		assertThat(dto.getBound()).isEqualToIgnoringCase(DirectionShortEum.OUTBOUND.getValue());
		assertThat(!dto.getIsCircularRoute());
		assertThat(stopDetailDtos.size()).isGreaterThan(2);
		assertThat(stopDetailDtos.get(2).getSeq()).isNotNull();
		assertThat(stopDetailDtos.get(3).getStopDto().getNameEn()).isNotNull();
	}

	@Test
	@DisplayName("ManagedManager.getRouteStopEtaByCompanyAndStopIdAndRouteAndDirection Test KMB")
	public void testGetRouteStopEtaByCompanyAndStopIdAndRouteAndDirectionKMB() throws Exception {
		ManagedRouteStopEtaResponse response = managedManager
				.getRouteStopEtaByCompanyAndStopIdAndRouteAndDirectionAndServiceType(BusCompanyEum.KMB.getValue(),
						UnitTestConfiguration.testKMB1AInboundStop(), UnitTestConfiguration.testKMBRoute1A(),
						DirectionFullEum.INBOUND.getValue(), "1");
		List<ManagedRouteStopEtaDto> dtos = response.getDtos();
		// assertThat(dtos.size()).isGreaterThan(0);
		// assertThat(dtos.get(0)).isNotNull();
		// assertThat(dtos.get(0).getDestinationEn()).isNotBlank();
	}

	// CTB
	@Test
	@DisplayName("ManagedManager.getRouteListByCompanyAndRouteAndServiceType Test CTB")
	public void testGetRouteListByCompanyAndRouteCTB() throws Exception {
		ManagedRouteListResponse response = managedManager.getRouteListByCompanyAndRouteAndServiceType(
				BusCompanyEum.CTB.getValue(), UnitTestConfiguration.testCTBRouteA29(), null);
		List<ManagedRouteDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testCTBRouteA29());
	}

	@Test
	@DisplayName("ManagedManager.getRouteByCompanyAndRouteAndDirectionAndServiceType Test CTB")
	public void testGetRouteByCompanyAndRouteAndDirectionCTB() throws Exception {
		ManagedRouteResponse response = managedManager.getRouteByCompanyAndRouteAndDirectionAndServiceType(
				BusCompanyEum.CTB.getValue(), UnitTestConfiguration.testCTBRoute671(),
				DirectionShortEum.OUTBOUND.getValue(), null);
		ManagedRouteDto dto = response.getDto();
		assertThat(dto.getOriginEn()).isNotBlank();
		assertThat(dto.getRoute()).isEqualTo(UnitTestConfiguration.testCTBRoute671());
	}

	@Test
	@DisplayName("ManagedManager.getRouteByCompanyAndRouteAndDirectionAndServiceType Test CTB Circular Route")
	public void testGetRouteByCompanyAndRouteAndDirectionCTBCircularRoute() throws Exception {
		ManagedRouteResponse response = managedManager.getRouteByCompanyAndRouteAndDirectionAndServiceType(
				BusCompanyEum.CTB.getValue(), UnitTestConfiguration.testCTBCircularRoute12A(),
				DirectionShortEum.INBOUND.getValue(), null);
		ManagedRouteDto dto = response.getDto();
		assertThat(dto.getOriginEn()).isNotBlank();
		assertThat(dto.getBound()).isEqualTo(DirectionShortEum.OUTBOUND.getValue());
		assertThat(dto.getRoute()).isEqualTo(UnitTestConfiguration.testCTBCircularRoute12A());
	}

	@Test
	@DisplayName("ManagedManager.getRouteStopListByCompanyAndRouteAndDirectionAndServiceType Test CTB")
	public void testGetRouteStopListByCompanyAndRouteAndDirectionCTB() throws Exception {
		ManagedRouteStopListResponse response = managedManager
				.getRouteStopListByCompanyAndRouteAndDirectionAndServiceType(BusCompanyEum.CTB.getValue(),
						UnitTestConfiguration.testCTBRoute671(), DirectionShortEum.OUTBOUND.getValue(), null);
		List<ManagedRouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testCTBRoute671());
		assertThat(dtos.get(2).getStop()).isNotBlank();
	}

	@Test
	@DisplayName("ManagedManager.getRouteDetailByCompanyAndRouteAndDirectionAndServiceType Test CTB")
	public void testGetRouteDetailByCompanyAndRouteAndDirectionCTB() throws Exception {
		ManagedRouteDetailResponse response = managedManager.getRouteDetailByCompanyAndRouteAndDirectionAndServiceType(
				BusCompanyEum.CTB.getValue(), UnitTestConfiguration.testCTBRouteA29(),
				DirectionShortEum.OUTBOUND.getValue(), null);
		ManagedRouteDetailDto dto = response.getDto();
		List<ManagedStopDetailDto> stopDetailDtos = dto.getStopDetailDtos();
		assertThat(dto.getBound()).isEqualToIgnoringCase(DirectionShortEum.OUTBOUND.getValue());
		assertThat(stopDetailDtos.size()).isGreaterThan(2);
		assertThat(stopDetailDtos.get(2).getSeq()).isNotNull();
		assertThat(stopDetailDtos.get(3).getStopDto().getNameEn()).isNotNull();
	}

	@Test
	@DisplayName("ManagedManager.getRouteStopEtaByCompanyAndStopIdAndRouteAndDirection Test CTB")
	public void testGetRouteStopEtaByCompanyAndStopIdAndRouteAndDirectionCTB() throws Exception {
		ManagedRouteStopEtaResponse response = managedManager
				.getRouteStopEtaByCompanyAndStopIdAndRouteAndDirectionAndServiceType(BusCompanyEum.CTB.getValue(),
						UnitTestConfiguration.testCTBA29InboundStop(), UnitTestConfiguration.testCTBRouteA29(),
						DirectionFullEum.INBOUND.getValue(), null);
		List<ManagedRouteStopEtaDto> dtos = response.getDtos();
		// assertThat(dtos.size()).isGreaterThan(0);
		// assertThat(dtos.get(0)).isNotNull();
		// assertThat(dtos.get(0).getDestinationEn()).isNotBlank();
	}

	// NWFB
	@Test
	@DisplayName("ManagedManager.getRouteListByCompanyAndRouteAndServiceType Test NWFB")
	public void testGetRouteListByCompanyAndRouteNWFB() throws Exception {
		ManagedRouteListResponse response = managedManager.getRouteListByCompanyAndRouteAndServiceType(
				BusCompanyEum.NWFB.getValue(), UnitTestConfiguration.testNWFBRoute15C(), null);
		List<ManagedRouteDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testNWFBRoute15C());
	}

	@Test
	@DisplayName("ManagedManager.getRouteByCompanyAndRouteAndDirectionAndServiceType Test NWFB")
	public void testGetRouteByCompanyAndRouteAndDirectionNWFB() throws Exception {
		ManagedRouteResponse response = managedManager.getRouteByCompanyAndRouteAndDirectionAndServiceType(
				BusCompanyEum.NWFB.getValue(), UnitTestConfiguration.testNWFBRoute18(),
				DirectionShortEum.OUTBOUND.getValue(), null);
		ManagedRouteDto dto = response.getDto();
		assertThat(dto.getOriginEn()).isNotBlank();
		assertThat(dto.getRoute()).isEqualTo(UnitTestConfiguration.testNWFBRoute18());
	}

	@Test
	@DisplayName("ManagedManager.getRouteStopListByCompanyAndRouteAndDirectionAndServiceType Test NWFB")
	public void testGetRouteStopListByCompanyAndRouteAndDirectionNWFB() throws Exception {
		ManagedRouteStopListResponse response = managedManager
				.getRouteStopListByCompanyAndRouteAndDirectionAndServiceType(BusCompanyEum.NWFB.getValue(),
						UnitTestConfiguration.testNWFBRoute15C(), DirectionShortEum.OUTBOUND.getValue(), null);
		List<ManagedRouteStopDto> dtos = response.getDtos();
		assertThat(dtos.size()).isGreaterThan(0);
		assertThat(dtos.get(1).getRoute()).isEqualTo(UnitTestConfiguration.testNWFBRoute15C());
		assertThat(dtos.get(2).getStop()).isNotBlank();
	}

	@Test
	@DisplayName("ManagedManager.getRouteDetailByCompanyAndRouteAndDirectionAndServiceType Test NWFB")
	public void testGetRouteDetailByCompanyAndRouteAndDirectionNWFB() throws Exception {
		ManagedRouteDetailResponse response = managedManager.getRouteDetailByCompanyAndRouteAndDirectionAndServiceType(
				BusCompanyEum.NWFB.getValue(), UnitTestConfiguration.testNWFBRoute18(),
				DirectionShortEum.OUTBOUND.getValue(), null);
		ManagedRouteDetailDto dto = response.getDto();
		List<ManagedStopDetailDto> stopDetailDtos = dto.getStopDetailDtos();
		assertThat(dto.getBound()).isEqualToIgnoringCase(DirectionShortEum.OUTBOUND.getValue());
		assertThat(stopDetailDtos.size()).isGreaterThan(2);
		assertThat(stopDetailDtos.get(2).getSeq()).isNotNull();
		assertThat(stopDetailDtos.get(3).getStopDto().getNameEn()).isNotNull();
	}

	@Test
	@DisplayName("ManagedManager.getRouteStopEtaByCompanyAndStopIdAndRouteAndDirection Test NWFB")
	public void testGetRouteStopEtaByCompanyAndStopIdAndRouteAndDirectionNWFB() throws Exception {
		ManagedRouteStopEtaResponse response = managedManager
				.getRouteStopEtaByCompanyAndStopIdAndRouteAndDirectionAndServiceType(BusCompanyEum.NWFB.getValue(),
						UnitTestConfiguration.testNWFB18OutboundStop(), UnitTestConfiguration.testNWFBRoute18(),
						DirectionFullEum.OUTBOUND.getValue(), null);
		List<ManagedRouteStopEtaDto> dtos = response.getDtos();
		// assertThat(dtos.size()).isGreaterThan(0);
		// assertThat(dtos.get(0)).isNotNull();
		// assertThat(dtos.get(0).getDestinationEn()).isNotBlank();
	}

	// CTB-NWFB Stop
	@Test
	@DisplayName("ManagedManager.getStopByCompanyAndStopId Test CTB")
	public void testGetStopByCompanyAndStopIdCTB() throws Exception {
		ManagedStopResponse response = managedManager.getStopByCompanyAndStopId(BusCompanyEum.CTB.getValue(),
				UnitTestConfiguration.testCTBNWFBStop());
		ManagedStopDto dto = response.getDto();
		assertThat(dto.getStop()).isEqualTo(UnitTestConfiguration.testCTBNWFBStop());
		assertThat(dto.getNameEn()).isNotEmpty();
	}

	// Get route by route
	@Test
	@DisplayName("ManagedManager.getRouteByRoute Test 101")
	public void testGetRouteByRoute() throws Exception {
		ManagedRouteListResponse response = managedManager.getRouteByRoute(UnitTestConfiguration.testKMBRoute101());
		List<ManagedRouteDto> dtos = response.getDtos();
		for (ManagedRouteDto dto : dtos) {
			assertThat(dto.getRoute()).contains(UnitTestConfiguration.testKMBRoute101());
			assertTrue(dto.getCompany().equalsIgnoreCase(BusCompanyEum.KMB.getValue())
					|| dto.getCompany().equalsIgnoreCase(BusCompanyEum.CTB.getValue())
					|| dto.getCompany().equalsIgnoreCase(BusCompanyEum.NWFB.getValue()));
		}

	}

}
