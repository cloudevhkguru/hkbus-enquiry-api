package org.cloudevguru.hkbus.enquiry.api.dto.managed;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagedRouteStopListResponse {

	@JsonProperty("result")
	private List<ManagedRouteStopDto> dtos;

	public List<ManagedRouteStopDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<ManagedRouteStopDto> dtos) {
		this.dtos = dtos;
	}

}
