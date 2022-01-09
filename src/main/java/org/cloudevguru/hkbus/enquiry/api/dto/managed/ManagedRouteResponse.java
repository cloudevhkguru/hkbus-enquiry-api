package org.cloudevguru.hkbus.enquiry.api.dto.managed;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagedRouteResponse {

	@JsonProperty("result")
	private ManagedRouteDto dto;

	public ManagedRouteDto getDto() {
		return dto;
	}

	public void setDto(ManagedRouteDto dto) {
		this.dto = dto;
	}

}
