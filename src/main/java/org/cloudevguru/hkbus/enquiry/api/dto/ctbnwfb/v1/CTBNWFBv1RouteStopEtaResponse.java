package org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CTBNWFBv1RouteStopEtaResponse extends CTBNWFBv1BaseResponse {

	@JsonProperty("data")
	private List<CTBNWFBv1RouteStopEtaDto> dtos;

	public List<CTBNWFBv1RouteStopEtaDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<CTBNWFBv1RouteStopEtaDto> dtos) {
		this.dtos = dtos;
	}

}
