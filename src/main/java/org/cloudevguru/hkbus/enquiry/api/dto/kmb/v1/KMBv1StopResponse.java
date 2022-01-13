package org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KMBv1StopResponse extends KMBv1BaseResponse{

	@JsonProperty("data")
	private KMBv1StopDto dto;

	public KMBv1StopDto getDto() {
		return dto;
	}

	public void setDto(KMBv1StopDto dto) {
		this.dto = dto;
	}

}
