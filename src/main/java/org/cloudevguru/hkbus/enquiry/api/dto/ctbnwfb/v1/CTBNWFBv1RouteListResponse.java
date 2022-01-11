package org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CTBNWFBv1RouteListResponse extends CTBNWFBv1BaseResponse{
	
	@JsonProperty("data")
	private List<CTBNWFBv1RouteDto> dtos;

	public List<CTBNWFBv1RouteDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<CTBNWFBv1RouteDto> dtos) {
		this.dtos = dtos;
	}
	
}
