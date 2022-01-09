package org.cloudevguru.hkbus.enquiry.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.cloudevguru.hkbus.enquiry.api.configuration.UnitTestConfiguration;
import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant.*;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.manager.KMBManager;
import org.junit.jupiter.api.BeforeEach;
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

}
