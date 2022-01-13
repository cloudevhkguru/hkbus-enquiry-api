package org.cloudevguru.hkbus.enquiry.api.dto.managed;

import java.util.List;

public class ManagedRouteStopEtaResponse {

	private ManagedRouteDto routeDto;
	
	private ManagedStopDto stopDto;
	
	private List<ManagedRouteStopEtaDto> routeStopEtaDtos;

	public ManagedRouteDto getRouteDto() {
		return routeDto;
	}

	public void setRouteDto(ManagedRouteDto routeDto) {
		this.routeDto = routeDto;
	}

	public ManagedStopDto getStopDto() {
		return stopDto;
	}

	public void setStopDto(ManagedStopDto stopDto) {
		this.stopDto = stopDto;
	}

	public List<ManagedRouteStopEtaDto> getRouteStopEtaDtos() {
		return routeStopEtaDtos;
	}

	public void setRouteStopEtaDtos(List<ManagedRouteStopEtaDto> routeStopEtaDtos) {
		this.routeStopEtaDtos = routeStopEtaDtos;
	}
	
}
