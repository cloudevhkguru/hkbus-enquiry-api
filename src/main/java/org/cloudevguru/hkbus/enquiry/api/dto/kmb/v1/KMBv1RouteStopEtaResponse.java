package org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KMBv1RouteStopEtaResponse extends KMBv1BaseResponse {

	@JsonProperty("data")
	private List<KMBv1RouteStopEtaDto> dtos;

	public List<KMBv1RouteStopEtaDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<KMBv1RouteStopEtaDto> dtos) {
		this.dtos = dtos;
	}

}
