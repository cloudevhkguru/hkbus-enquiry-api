package org.cloudevguru.hkbus.enquiry.api.dto.managed;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagedRouteDetailResponse {
	
	@JsonProperty("result")
	private ManagedRouteDetailDto dto;

	public ManagedRouteDetailDto getDto() {
		return dto;
	}

	public void setDto(ManagedRouteDetailDto dto) {
		this.dto = dto;
	}
	
}
