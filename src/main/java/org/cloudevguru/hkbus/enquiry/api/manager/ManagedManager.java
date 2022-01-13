package org.cloudevguru.hkbus.enquiry.api.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.cloudevguru.hkbus.enquiry.api.service.UtilityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagedManager {

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private KMBManager kmbManager;

	@Autowired
	private CTBNWFBManager ctbnwfbManager;

	// route
	public ManagedRouteListResponse getRouteListByCompanyAndRoute(String company, String route) {
		utilityService.checkIsValidBusCompany(company);
		ManagedRouteListResponse managedResponse = new ManagedRouteListResponse();
		List<ManagedRouteDto> managedRouteDtos = new ArrayList<ManagedRouteDto>();
		Boolean isCircularRoute = false;
		if (company.equalsIgnoreCase(BusCompanyEum.KMB.getValue())) {
			KMBv1RouteListResponse kmbResponse = kmbManager.getKMBv1RouteListByRoute(route);
			if (kmbResponse.getDtos().size() == 1) {
				isCircularRoute = true;
			}
			for (KMBv1RouteDto kmbRouteDto : kmbResponse.getDtos()) {
				ManagedRouteDto managedRouteDto = modelMapper.map(kmbRouteDto, ManagedRouteDto.class);
				managedRouteDto.setCompany(BusCompanyEum.KMB.getValue());
				managedRouteDto.setIsCircularRoute(isCircularRoute);
				managedRouteDtos.add(managedRouteDto);
			}
		} else {
			CTBNWFBv1RouteListResponse ctbnwfbResponse = ctbnwfbManager.getCTBNWFBv1RouteListByCompanyAndRoute(company,
					route);
			if (ctbnwfbResponse.getDtos().size() == 1) {
				isCircularRoute = true;
			}
			for (CTBNWFBv1RouteDto ctbnwfbRouteDto : ctbnwfbResponse.getDtos()) {
				ManagedRouteDto managedRouteDto = modelMapper.map(ctbnwfbRouteDto, ManagedRouteDto.class);
				managedRouteDto.setCompany(company.toLowerCase());
				managedRouteDto.setIsCircularRoute(isCircularRoute);
				managedRouteDtos.add(managedRouteDto);
			}
		}
		managedResponse.setDtos(managedRouteDtos);
		return managedResponse;
	}

	public ManagedRouteResponse getRouteByCompanyAndRouteAndDirection(String company, String route, String direction) {
		utilityService.checkIsValidBusCompany(company);
		String directionFull = utilityService.convertDirectionToFull(direction);
		ManagedRouteResponse managedResponse = new ManagedRouteResponse();
		ManagedRouteListResponse managedRouteListResponse = getRouteListByCompanyAndRoute(company, route);
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
	public ManagedRouteStopListResponse getRouteStopListByCompanyAndRouteAndDirection(String company, String route,
			String direction) {
		utilityService.checkIsValidBusCompany(company);
		ManagedRouteStopListResponse managedResponse = new ManagedRouteStopListResponse();
		List<ManagedRouteStopDto> managedRouteStopDtos = new ArrayList<ManagedRouteStopDto>();
		if (company.equalsIgnoreCase(BusCompanyEum.KMB.getValue())) {
			KMBv1RouteStopListResponse kmbResponse = kmbManager.getKMBv1RouteStopListByRouteAndDirection(route,
					direction);
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
	public ManagedRouteDetailResponse getRouteDetailByCompanyAndRouteAndDirection(String company, String route,
			String direction) {
		ManagedRouteDetailResponse response = new ManagedRouteDetailResponse();
		ManagedRouteDto managedRouteDto = getRouteByCompanyAndRouteAndDirection(company, route, direction).getDto();
		if (managedRouteDto.getCompany() != null) {
			String directionFull = utilityService.convertDirectionToFull(managedRouteDto.getBound());
			ManagedRouteDetailDto manageRouteDetailDto = modelMapper.map(managedRouteDto, ManagedRouteDetailDto.class);
			List<ManagedRouteStopDto> managedRouteStopDtos = getRouteStopListByCompanyAndRouteAndDirection(company,
					route, directionFull).getDtos();
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
		}
		return response;
	}

	// Route Stop ETA
	public ManagedRouteStopEtaResponse getRouteStopEtaByCompanyAndStopIdAndRouteAndDirection(String company,
			String stopId, String route, String direction) {
		utilityService.checkIsValidBusCompany(company);
		ManagedRouteStopEtaResponse response = new ManagedRouteStopEtaResponse();
		ManagedRouteResponse routeResponse=getRouteByCompanyAndRouteAndDirection(company, route, direction);
		ManagedStopResponse stopResponse=getStopByCompanyAndStopId(company, stopId);
		List<ManagedRouteStopEtaDto> routeStopEtaDtos=new ArrayList<ManagedRouteStopEtaDto>();
		Date currenDate=new Date();
		if(company.equalsIgnoreCase(BusCompanyEum.KMB.getValue())) {
			KMBv1RouteStopEtaResponse kmbV1RouteStopEtaResponse=kmbManager.getKMBv1RouteStopEtaByRouteAndStopId(stopId, route);
			List<KMBv1RouteStopEtaDto> kmbRouteStopEtaDtos=kmbV1RouteStopEtaResponse.getDtos();
			for(KMBv1RouteStopEtaDto kmbRouteStopEtaDto:kmbRouteStopEtaDtos) {
				ManagedRouteStopEtaDto routeStopEtaDto=modelMapper.map(kmbRouteStopEtaDto, ManagedRouteStopEtaDto.class);
				if(kmbRouteStopEtaDto.getEta()!=null) {
					Date eta=kmbRouteStopEtaDto.getEta();
					Long millsecondsDiff=eta.getTime()-currenDate.getTime();
					Integer minutesDifference=(int) Math.floor(millsecondsDiff/60000);
					routeStopEtaDto.setMinutes(minutesDifference);
				}
				routeStopEtaDtos.add(routeStopEtaDto);
			}
			
		}else{
			CTBNWFBv1RouteStopEtaResponse ctbnwfbv1RouteStopEtaResponse=ctbnwfbManager.getCTBNWFBv1RouteStopEtaByCompanyAndStopIdAndRoute(company, stopId, route);
			List<CTBNWFBv1RouteStopEtaDto> ctbnwfbRouteStopEtaDtos=ctbnwfbv1RouteStopEtaResponse.getDtos();
			for(CTBNWFBv1RouteStopEtaDto ctbnwfbRouteStopEtaDto:ctbnwfbRouteStopEtaDtos) {
				ManagedRouteStopEtaDto routeStopEtaDto=modelMapper.map(ctbnwfbRouteStopEtaDto, ManagedRouteStopEtaDto.class);
				if(ctbnwfbRouteStopEtaDto.getEta()!=null) {
					Date eta=ctbnwfbRouteStopEtaDto.getEta();
					Long millsecondsDiff=eta.getTime()-currenDate.getTime();
					Integer minutesDifference=(int) Math.floor(millsecondsDiff/60000);
					routeStopEtaDto.setMinutes(minutesDifference);
				}
				routeStopEtaDtos.add(routeStopEtaDto);
			}
		}
		response.setRouteDto(routeResponse.getDto());
		response.setStopDto(stopResponse.getDto());
		response.setRouteStopEtaDtos(routeStopEtaDtos);
		return response;
	}

}
