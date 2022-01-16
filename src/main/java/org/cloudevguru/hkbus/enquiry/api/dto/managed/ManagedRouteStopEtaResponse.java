package org.cloudevguru.hkbus.enquiry.api.dto.managed;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagedRouteStopEtaResponse {

	@JsonProperty("result")
	private List<ManagedRouteStopEtaDto> dtos;

	public List<ManagedRouteStopEtaDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<ManagedRouteStopEtaDto> dtos) {
		this.dtos = dtos;
	}
	
}
