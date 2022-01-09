package org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CTBNWFBv1RouteStopListResponse {

	@JsonProperty("type")
	private String type;

	@JsonProperty("version")
	private String version;

	@JsonProperty("generated_timestamp")
	private String generatedTimestamp;

	@JsonProperty("data")
	private List<CTBNWFBv1RouteStopDto> dtos;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGeneratedTimestamp() {
		return generatedTimestamp;
	}

	public void setGeneratedTimestamp(String generatedTimestamp) {
		this.generatedTimestamp = generatedTimestamp;
	}

	public List<CTBNWFBv1RouteStopDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<CTBNWFBv1RouteStopDto> dtos) {
		this.dtos = dtos;
	}

}
