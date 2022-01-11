package org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KMBv1StopListResponse extends KMBv1BaseResponse {

	@JsonProperty("data")
	private List<KMBv1StopDto> dtos;

	public List<KMBv1StopDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<KMBv1StopDto> dtos) {
		this.dtos = dtos;
	}

}
