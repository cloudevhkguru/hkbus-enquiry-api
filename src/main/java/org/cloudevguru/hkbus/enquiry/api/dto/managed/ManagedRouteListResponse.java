package org.cloudevguru.hkbus.enquiry.api.dto.managed;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagedRouteListResponse {
	
	@JsonProperty("result")
	private List<ManagedRouteDto> dtos;

	public List<ManagedRouteDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<ManagedRouteDto> dtos) {
		this.dtos = dtos;
	}

}
