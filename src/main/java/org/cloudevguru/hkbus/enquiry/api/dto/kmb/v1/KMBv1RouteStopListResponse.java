package org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KMBv1RouteStopListResponse extends KMBv1BaseResponse{

	@JsonProperty("data")
	private List<KMBv1RouteStopDto> dtos;

	public List<KMBv1RouteStopDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<KMBv1RouteStopDto> dtos) {
		this.dtos = dtos;
	}

}
