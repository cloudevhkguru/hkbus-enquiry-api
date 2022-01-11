package org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CTBNWFBv1RouteResponse extends CTBNWFBv1BaseResponse{

	@JsonProperty("data")
	private CTBNWFBv1RouteDto dto;

	public CTBNWFBv1RouteDto getDto() {
		return dto;
	}

	public void setDto(CTBNWFBv1RouteDto dto) {
		this.dto = dto;
	}

}
