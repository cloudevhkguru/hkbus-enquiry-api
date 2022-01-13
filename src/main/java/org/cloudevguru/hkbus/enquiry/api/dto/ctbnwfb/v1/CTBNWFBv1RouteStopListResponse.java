package org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CTBNWFBv1RouteStopListResponse extends CTBNWFBv1BaseResponse {

	@JsonProperty("data")
	private List<CTBNWFBv1RouteStopDto> dtos;

	public List<CTBNWFBv1RouteStopDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<CTBNWFBv1RouteStopDto> dtos) {
		this.dtos = dtos;
	}

}
