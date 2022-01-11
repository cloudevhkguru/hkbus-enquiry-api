package org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KMBv1RouteListResponse extends KMBv1BaseResponse {

	@JsonProperty("data")
	private List<KMBv1RouteDto> dtos;

	public List<KMBv1RouteDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<KMBv1RouteDto> dtos) {
		this.dtos = dtos;
	}

}
