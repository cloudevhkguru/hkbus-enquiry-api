package org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CTBNWFBv1StopResponse extends CTBNWFBv1BaseResponse {

	@JsonProperty("data")
	private CTBNWFBv1StopDto dto;

	public CTBNWFBv1StopDto getDto() {
		return dto;
	}

	public void setDto(CTBNWFBv1StopDto dto) {
		this.dto = dto;
	}

}
