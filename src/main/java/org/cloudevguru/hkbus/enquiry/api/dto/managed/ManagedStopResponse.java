package org.cloudevguru.hkbus.enquiry.api.dto.managed;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagedStopResponse {

	@JsonProperty("result")
	private ManagedStopDto dto;

	public ManagedStopDto getDto() {
		return dto;
	}

	public void setDto(ManagedStopDto dto) {
		this.dto = dto;
	}

}
