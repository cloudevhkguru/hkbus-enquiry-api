package org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KMBv1RouteResponse extends KMBv1BaseResponse{

	@JsonProperty("data")
	private KMBv1RouteDto dto;

	public KMBv1RouteDto getDto() {
		return dto;
	}

	public void setDto(KMBv1RouteDto dto) {
		this.dto = dto;
	}

}
