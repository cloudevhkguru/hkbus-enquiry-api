package org.cloudevguru.hkbus.enquiry.api.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant.*;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopEtaDto;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopEtaResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1.CTBNWFBv1StopResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopEtaDto;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopEtaResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1RouteStopListResponse;
import org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1.KMBv1StopResponse;
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
import org.cloudevguru.hkbus.enquiry.api.dto.routefare.v1.RouteFareDto;
import org.cloudevguru.hkbus.enquiry.api.service.ConcurrentHashMapService;
import org.cloudevguru.hkbus.enquiry.api.service.RouteFareService;
import org.cloudevguru.hkbus.enquiry.api.service.UtilityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagedManager {

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private RouteFareService routeFareService;

	@Autowired
	private ConcurrentHashMapService concurrentHashMapService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private KMBManager kmbManager;

	@Autowired
	private CTBNWFBManager ctbnwfbManager;

	// route
	private ManagedRouteListResponse getAllRoute() {
		ManagedRouteListResponse managedResponse = new ManagedRouteListResponse();
		List<ManagedRouteDto> managedRouteDtos = new ArrayList<ManagedRouteDto>();
		if (concurrentHashMapService.isEmptyRawRouteChm()) {
			KMBv1RouteListResponse kmbv1RouteListResponse = kmbManager.getKMBv1RouteList();
			List<KMBv1RouteDto> kmbRouteDtos = kmbv1RouteListResponse.getDtos();
			for (KMBv1RouteDto kmbRoute : kmbRouteDtos) {
				ManagedRouteDto kmbManagedRouteDto = convertKmbRouteDtoToManagedRouteDto(kmbRoute, null, null);
				kmbManagedRouteDto.setCompany(BusCompanyEum.KMB.getValue().toUpperCase());
				String key = getRouteKeyForManagedRouteDtoChm(kmbManagedRouteDto);
				concurrentHashMapService.putRawRouteToRawRouteChm(key, kmbManagedRouteDto);
				managedRouteDtos.add(kmbManagedRouteDto);
			}
			String[] companys = new String[] { BusCompanyEum.CTB.getValue(), BusCompanyEum.NWFB.getValue() };
			for (String company : companys) {
				CTBNWFBv1RouteListResponse ctbnwfbv1RouteListResponse = ctbnwfbManager
						.getCTBNWFBv1AllRoutesByCompany(company);
				List<CTBNWFBv1RouteDto> ctbnwfbRouteDtos = ctbnwfbv1RouteListResponse.getDtos();
				for (CTBNWFBv1RouteDto ctbnwfbRoute : ctbnwfbRouteDtos) {
					ManagedRouteDto ctbManagedRouteDto = convertCtbnwfbRouteDtoToManagedRouteDto(ctbnwfbRoute, null,
							null);
					String key = getRouteKeyForManagedRouteDtoChm(ctbManagedRouteDto);
					concurrentHashMapService.putRawRouteToRawRouteChm(key, ctbManagedRouteDto);
					managedRouteDtos.add(ctbManagedRouteDto);
				}
			}
		} else {
			managedRouteDtos=concurrentHashMapService.getAllRawRouteFromRawRouteChm();
		}
		managedResponse.setDtos(managedRouteDtos);
		return managedResponse;
	}

	public ManagedRouteListResponse getRouteListByCompanyAndRouteAndServiceType(String company, String route,
			String serviceType) {
		utilityService.checkIsValidBusCompany(company);
		ManagedRouteListResponse managedResponse = new ManagedRouteListResponse();
		List<ManagedRouteDto> managedRouteDtos = new ArrayList<ManagedRouteDto>();
		Boolean isCircularRoute = false;
		if (company.equalsIgnoreCase(BusCompanyEum.KMB.getValue())) {
			KMBv1RouteListResponse kmbResponse = kmbManager.getKMBv1RouteListByRouteAndServiceType(route, serviceType);
			if (kmbResponse.getDtos().size() == 1) {
				isCircularRoute = true;
			}
			for (KMBv1RouteDto kmbRouteDto : kmbResponse.getDtos()) {
				String routeFareKey = getRouteFareKey(company, route, kmbRouteDto.getOriginEn(),
						kmbRouteDto.getDestinationEn());
				RouteFareDto routeFareDto = getRouteFareByRouteFareKey(routeFareKey);
				managedRouteDtos.add(
						convertKmbRouteDtoToManagedRouteDto(kmbRouteDto, isCircularRoute, routeFareDto.getFullFare()));
			}
		} else {
			CTBNWFBv1RouteListResponse ctbnwfbResponse = ctbnwfbManager.getCTBNWFBv1RouteListByCompanyAndRoute(company,
					route);
			if (ctbnwfbResponse.getDtos().size() != 0) {
				if (ctbnwfbResponse.getDtos().size() == 1) {
					isCircularRoute = true;
				}
				for (CTBNWFBv1RouteDto ctbnwfbRouteDto : ctbnwfbResponse.getDtos()) {
					String routeFareKey = getRouteFareKey(company, route, ctbnwfbRouteDto.getOriginEn(),
							ctbnwfbRouteDto.getDestinationEn());
					RouteFareDto routeFareDto = getRouteFareByRouteFareKey(routeFareKey);
					managedRouteDtos.add(convertCtbnwfbRouteDtoToManagedRouteDto(ctbnwfbRouteDto, isCircularRoute,
							routeFareDto.getFullFare()));
				}
			}
		}
		managedResponse.setDtos(managedRouteDtos);
		return managedResponse;
	}

	public ManagedRouteResponse getRouteByCompanyAndRouteAndDirectionAndServiceType(String company, String route,
			String direction, String serviceType) {
		utilityService.checkIsValidBusCompany(company);
		String directionFull = utilityService.convertDirectionToFull(direction);
		ManagedRouteResponse managedResponse = new ManagedRouteResponse();
		ManagedRouteListResponse managedRouteListResponse = getRouteListByCompanyAndRouteAndServiceType(company, route,
				serviceType);
		ManagedRouteDto managedRouteDto = null;
		if (managedRouteListResponse.getDtos().size() == 2) {
			// If have both inbound and outbound, then get the managedRouteDto matching
			// direction
			for (ManagedRouteDto managedRouteDtoInList : managedRouteListResponse.getDtos()) {
				if (utilityService.convertDirectionToFull(managedRouteDtoInList.getBound())
						.equalsIgnoreCase(directionFull)) {
					managedRouteDto = managedRouteDtoInList;
					if (company.equalsIgnoreCase(BusCompanyEum.KMB.getValue())) {
						managedRouteDto.setCompany(BusCompanyEum.KMB.getValue());
					} else {
						managedRouteDto.setCompany(company.toLowerCase());
					}
					break;
				}
			}
		} else if (managedRouteListResponse.getDtos().size() == 1) {
			// If only one managedRouteDto then use
			managedRouteDto = managedRouteListResponse.getDtos().get(0);
			if (company.equalsIgnoreCase(BusCompanyEum.KMB.getValue())) {
				managedRouteDto.setCompany(BusCompanyEum.KMB.getValue());
			} else {
				managedRouteDto.setCompany(company.toLowerCase());
			}
		} else {
			managedRouteDto = null;
		}
		managedResponse.setDto(managedRouteDto);
		return managedResponse;
	}

	public ManagedRouteListResponse getRouteByRoute(String route) {
		ManagedRouteListResponse managedResponse = new ManagedRouteListResponse();
		List<ManagedRouteDto> initialRouteList = new ArrayList<ManagedRouteDto>();
		String routListByRouteKey = "ROUTELISTBYROUTE" + route.toUpperCase();
		if (concurrentHashMapService.getRouteListFromRouteListChmByRouteKey(routListByRouteKey) != null
				&& concurrentHashMapService.getRouteListFromRouteListChmByRouteKey(routListByRouteKey).size() != 0) {
			// Find in ConcurrentHashMap, return result directly
			initialRouteList = concurrentHashMapService.getRouteListFromRouteListChmByRouteKey(routListByRouteKey);
			System.out.println(String.format("Find ROUTE-LIST-BY-ROUTE in ConcurrentHashMap for %s", route));
			managedResponse.setDtos(initialRouteList);
			return managedResponse;
		}
		// Get from Raw Route List
		getAllRoute();
		initialRouteList = concurrentHashMapService.getRawRouteFromRawRouteChmByRouteStartWith(route);
		ArrayList<ManagedRouteDto> finalRouteList = new ArrayList<ManagedRouteDto>();
		for (ManagedRouteDto routeDto : initialRouteList) {
			if (routeDto.getBound() == null) {
				String company2 = routeDto.getCompany();
				String route2 = routeDto.getRoute();
				List<ManagedRouteDto> additionalManagedRouteDtos = getRouteListByCompanyAndRouteAndServiceType(company2,
						route2, null).getDtos();
				for (ManagedRouteDto additioManagedRouteDto : additionalManagedRouteDtos) {
					String routeFareKey = getRouteFareKey(additioManagedRouteDto.getCompany(),
							additioManagedRouteDto.getRoute(), additioManagedRouteDto.getOriginEn(),
							additioManagedRouteDto.getDestinationEn());
					RouteFareDto routeFareDto = getRouteFareByRouteFareKey(routeFareKey);
					additioManagedRouteDto.setFullFare(routeFareDto.getFullFare());
					finalRouteList.add(additioManagedRouteDto);
				}
			} else {
				String routeFareKey = getRouteFareKey(routeDto.getCompany(), routeDto.getRoute(),
						routeDto.getOriginEn(), routeDto.getDestinationEn());
				RouteFareDto routeFareDto = getRouteFareByRouteFareKey(routeFareKey);
				routeDto.setFullFare(routeFareDto.getFullFare());
				finalRouteList.add(routeDto);
			}
		}

		if (finalRouteList.size() > 0) {
			concurrentHashMapService.putRouteListToRouteListChm(routListByRouteKey, finalRouteList);
		}
		managedResponse.setDtos(finalRouteList);
		return managedResponse;
	}

	// Stop
	public ManagedStopResponse getStopByCompanyAndStopId(String company, String stopId) {
		utilityService.checkIsValidBusCompany(company);
		ManagedStopResponse managedResponse = new ManagedStopResponse();
		if (company.equalsIgnoreCase(BusCompanyEum.KMB.getValue())) {
			KMBv1StopResponse kmbResponse = kmbManager.getKMBv1StopByStopId(stopId);
			ManagedStopDto managedStopDto = modelMapper.map(kmbResponse.getDto(), ManagedStopDto.class);
			managedStopDto.setCompany(BusCompanyEum.KMB.getValue());
			managedResponse.setDto(managedStopDto);
		} else {
			CTBNWFBv1StopResponse ctbnwfbResponse = ctbnwfbManager.getCTBNWFBv1StopByStopId(stopId);
			ManagedStopDto managedStopDto = modelMapper.map(ctbnwfbResponse.getDto(), ManagedStopDto.class);
			managedStopDto.setCompany(company.toLowerCase());
			managedResponse.setDto(managedStopDto);
		}
		return managedResponse;
	}

	// RouteStop
	public ManagedRouteStopListResponse getRouteStopListByCompanyAndRouteAndDirectionAndServiceType(String company,
			String route, String direction, String serviceType) {
		utilityService.checkIsValidBusCompany(company);
		ManagedRouteStopListResponse managedResponse = new ManagedRouteStopListResponse();
		List<ManagedRouteStopDto> managedRouteStopDtos = new ArrayList<ManagedRouteStopDto>();
		if (company.equalsIgnoreCase(BusCompanyEum.KMB.getValue())) {
			KMBv1RouteStopListResponse kmbResponse = kmbManager
					.getKMBv1RouteStopListByRouteAndDirectionAndServiceType(route, direction, serviceType);
			for (KMBv1RouteStopDto kmbRouteStopDto : kmbResponse.getDtos()) {
				ManagedRouteStopDto managedRouteStopDto = modelMapper.map(kmbRouteStopDto, ManagedRouteStopDto.class);
				managedRouteStopDto.setCompany(BusCompanyEum.KMB.getValue());
				managedRouteStopDtos.add(managedRouteStopDto);
			}
		} else {
			CTBNWFBv1RouteStopListResponse ctbnwfbResponse = ctbnwfbManager
					.getCTBNWFBv1RouteStopListByCompanyAndRouteAndDirection(company, route, direction);
			for (CTBNWFBv1RouteStopDto ctbnwfbRouteStopDto : ctbnwfbResponse.getDtos()) {
				ManagedRouteStopDto managedRouteStopDto = modelMapper.map(ctbnwfbRouteStopDto,
						ManagedRouteStopDto.class);
				managedRouteStopDto.setCompany(company.toLowerCase());
				managedRouteStopDtos.add(managedRouteStopDto);
			}
		}
		managedResponse.setDtos(managedRouteStopDtos);
		return managedResponse;
	}

	// Route Detail
	public ManagedRouteDetailResponse getRouteDetailByCompanyAndRouteAndDirectionAndServiceType(String company,
			String route, String direction, String serviceType) {
		ManagedRouteDetailResponse response = new ManagedRouteDetailResponse();
		String routeDetailKey = getRouteDetailKeyForManagedRouteDetailChm(company, route, direction, serviceType);
		if (concurrentHashMapService.getRouteDetailResponseFromRouteDetailChm(routeDetailKey) != null) {
			System.out.println(String.format("Find ROUTE-DETAIL in ConcurrentHashMap for %s", routeDetailKey));
			response = concurrentHashMapService.getRouteDetailResponseFromRouteDetailChm(routeDetailKey);
		} else {
			System.out.println(String.format("Cannot find ROUTE-DETAIL in ConcurrentHashMap for %s", routeDetailKey));
			ManagedRouteDto managedRouteDto = getRouteByCompanyAndRouteAndDirectionAndServiceType(company, route,
					direction, serviceType).getDto();
			if (managedRouteDto != null) {
				String directionFull = utilityService.convertDirectionToFull(managedRouteDto.getBound());
				ManagedRouteDetailDto manageRouteDetailDto = modelMapper.map(managedRouteDto,
						ManagedRouteDetailDto.class);
				List<ManagedRouteStopDto> managedRouteStopDtos = getRouteStopListByCompanyAndRouteAndDirectionAndServiceType(
						company, route, directionFull, serviceType).getDtos();
				List<ManagedStopDetailDto> managedStopDetailDtos = new ArrayList<ManagedStopDetailDto>();
				for (ManagedRouteStopDto managedRouteStopDto : managedRouteStopDtos) {
					ManagedStopDto managedStopDto = getStopByCompanyAndStopId(company, managedRouteStopDto.getStop())
							.getDto();
					ManagedStopDetailDto managedStopDetailDto = new ManagedStopDetailDto();
					managedStopDetailDto.setStopDto(managedStopDto);
					managedStopDetailDto.setSeq(Integer.valueOf(managedRouteStopDto.getSeq()));
					managedStopDetailDtos.add(managedStopDetailDto);
				}
				manageRouteDetailDto.setStopDetailDtos(managedStopDetailDtos);
				response.setDto(manageRouteDetailDto);
				concurrentHashMapService.putRouteDetailResponseToRouteDetailChm(routeDetailKey, response);
			}
		}
		return response;
	}

	// Route Stop ETA
	public ManagedRouteStopEtaResponse getRouteStopEtaByCompanyAndStopIdAndRouteAndDirectionAndServiceType(
			String company, String stopId, String route, String direction, String serviceType) {
		utilityService.checkIsValidBusCompany(company);
		ManagedRouteStopEtaResponse response = new ManagedRouteStopEtaResponse();
		List<ManagedRouteStopEtaDto> routeStopEtaDtos = new ArrayList<ManagedRouteStopEtaDto>();
		Date currenDate = new Date();
		if (company.equalsIgnoreCase(BusCompanyEum.KMB.getValue())) {
			KMBv1RouteStopEtaResponse kmbV1RouteStopEtaResponse = kmbManager
					.getKMBv1RouteStopEtaByRouteAndStopIdAndServiceType(stopId, route, serviceType);
			List<KMBv1RouteStopEtaDto> kmbRouteStopEtaDtos = kmbV1RouteStopEtaResponse.getDtos();
			for (KMBv1RouteStopEtaDto kmbRouteStopEtaDto : kmbRouteStopEtaDtos) {
				ManagedRouteStopEtaDto routeStopEtaDto = modelMapper.map(kmbRouteStopEtaDto,
						ManagedRouteStopEtaDto.class);
				if (kmbRouteStopEtaDto.getEta() != null) {
					Date eta = kmbRouteStopEtaDto.getEta();
					Long millsecondsDiff = eta.getTime() - currenDate.getTime();
					Integer minutesDifference = (int) Math.max(Math.floor(millsecondsDiff / 60000), 0);
					routeStopEtaDto.setMinutes(minutesDifference);
				}
				routeStopEtaDtos.add(routeStopEtaDto);
			}

		} else {
			CTBNWFBv1RouteStopEtaResponse ctbnwfbv1RouteStopEtaResponse = ctbnwfbManager
					.getCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRoute(company, stopId, route);
			List<CTBNWFBv1RouteStopEtaDto> ctbnwfbRouteStopEtaDtos = ctbnwfbv1RouteStopEtaResponse.getDtos();
			for (CTBNWFBv1RouteStopEtaDto ctbnwfbRouteStopEtaDto : ctbnwfbRouteStopEtaDtos) {
				ManagedRouteStopEtaDto routeStopEtaDto = modelMapper.map(ctbnwfbRouteStopEtaDto,
						ManagedRouteStopEtaDto.class);
				if (ctbnwfbRouteStopEtaDto.getEta() != null) {
					Date eta = ctbnwfbRouteStopEtaDto.getEta();
					Long millsecondsDiff = eta.getTime() - currenDate.getTime();
					Integer minutesDifference = (int) Math.floor(millsecondsDiff / 60000);
					routeStopEtaDto.setMinutes(minutesDifference);
				}
				routeStopEtaDtos.add(routeStopEtaDto);
			}
		}
		response.setDtos(routeStopEtaDtos);
		return response;
	}

	// RouteFare
	private List<RouteFareDto> getAllRouteFare() {
		List<RouteFareDto> routeFareDtos = new ArrayList<RouteFareDto>();
		List<RouteFareDto> dtos = routeFareService.getAllBusesRouteFare();
		for (RouteFareDto dto : dtos) {
			String[] companys = dto.getCompany().split("\\+");
			for (String company : companys) {
				RouteFareDto clonedDto;
				try {
					clonedDto = (RouteFareDto) dto.clone();
					clonedDto.setCompany(company);
					String routeFareKey = getRouteFareKey(clonedDto);
					concurrentHashMapService.putRouteFareDtoTorouteFareDtoChm(routeFareKey, clonedDto);
					routeFareDtos.add(clonedDto);
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return routeFareDtos;
	}

	private RouteFareDto getRouteFareByRouteFareKey(String routeFareKey) {
		if (concurrentHashMapService.isEmptyRouteFareDtoChm()) {
			getAllRouteFare();
		}
		String[] routeDetail = routeFareKey.split("-");
		String reverseRouteFareKey = getRouteFareKey(routeDetail[0], routeDetail[1], routeDetail[3], routeDetail[2]);
		if (concurrentHashMapService.getRouteFareDtoFromrouteFareDtoChmByRouteFareKey(routeFareKey) != null) {
			return concurrentHashMapService.getRouteFareDtoFromrouteFareDtoChmByRouteFareKey(routeFareKey);
		} else if (concurrentHashMapService
				.getRouteFareDtoFromrouteFareDtoChmByRouteFareKey(reverseRouteFareKey) != null) {
			return concurrentHashMapService.getRouteFareDtoFromrouteFareDtoChmByRouteFareKey(reverseRouteFareKey);
		} else {
			return new RouteFareDto();
		}
	}

	public List<RouteFareDto> getRouteFareByRoute(String route) {
		List<RouteFareDto> dtos = getAllRouteFare();
		List<RouteFareDto> routeFareDtos = new ArrayList<RouteFareDto>();
		for (RouteFareDto dto : dtos) {
			if (dto.getRouteNameEn().equalsIgnoreCase(route)) {
				routeFareDtos.add(dto);
			}
		}
		return routeFareDtos;
	}

	private ManagedRouteDto convertKmbRouteDtoToManagedRouteDto(KMBv1RouteDto kmbv1RouteDto, Boolean isCircularRoute,
			BigDecimal fullFare) {
		String company = BusCompanyEum.KMB.getValue().toUpperCase();
		ManagedRouteDto managedRouteDto = modelMapper.map(kmbv1RouteDto, ManagedRouteDto.class);
		managedRouteDto.setCompany(company);
		managedRouteDto.setIsCircularRoute(isCircularRoute);
		managedRouteDto.setFullFare(fullFare);
		return managedRouteDto;
	}

	private ManagedRouteDto convertCtbnwfbRouteDtoToManagedRouteDto(CTBNWFBv1RouteDto ctbnwfbv1RouteDto,
			Boolean isCircularRoute, BigDecimal fullFare) {
		ManagedRouteDto managedRouteDto = modelMapper.map(ctbnwfbv1RouteDto, ManagedRouteDto.class);
		managedRouteDto.setIsCircularRoute(isCircularRoute);
		managedRouteDto.setFullFare(fullFare);
		return managedRouteDto;
	}

	private String getRouteKeyForManagedRouteDtoChm(ManagedRouteDto dto) {
		String modifiedDirection;
		String modifiedServiceType;
		if (dto.getBound() == null || dto.getBound().isEmpty()) {
			modifiedDirection = "";
		} else {
			modifiedDirection = utilityService.convertDirectionToFull(dto.getBound());
		}
		if (dto.getServiceType() == null || dto.getServiceType().isEmpty()) {
			modifiedServiceType = "";
		} else {
			modifiedServiceType = dto.getServiceType();
		}
		return String.format("%s-%s-%s-%s", dto.getCompany().toUpperCase(), dto.getRoute().toUpperCase(),
				modifiedDirection, modifiedServiceType);
	}

	private String getRouteDetailKeyForManagedRouteDetailChm(String company, String route, String direction,
			String serviceType) {
		String modifiedDirection;
		String modifiedServiceType;
		if (direction == null || direction.isEmpty()) {
			modifiedDirection = "na";
		} else {
			modifiedDirection = utilityService.convertDirectionToFull(direction);
		}
		if (serviceType == null || serviceType.isEmpty()) {
			modifiedServiceType = "na";
		} else {
			modifiedServiceType = serviceType;
		}
		return String.format("%s-%s-%s-%s", company.toUpperCase(), route.toUpperCase(), modifiedDirection,
				modifiedServiceType);
	}

	private String getRouteFareKey(RouteFareDto routeFareDto) {
		String modifiedCompany;
		if (routeFareDto.getCompany().equalsIgnoreCase(BusCompanyEum.LWB.getValue())) {
			modifiedCompany = BusCompanyEum.KMB.getValue().toUpperCase();
		} else {
			modifiedCompany = routeFareDto.getCompany().toUpperCase();
		}
		return getRouteFareKey(modifiedCompany, routeFareDto.getRouteNameEn(), routeFareDto.getOriginEn(),
				routeFareDto.getDestinationEn());
	}

	private String getRouteFareKey(String company, String route, String originEn, String destinationEn) {
		return String.format("%s-%s-%s-%s", company, route, utilityService.removeSpace(originEn),
				utilityService.removeSpace(destinationEn)).toUpperCase();
	}
}
