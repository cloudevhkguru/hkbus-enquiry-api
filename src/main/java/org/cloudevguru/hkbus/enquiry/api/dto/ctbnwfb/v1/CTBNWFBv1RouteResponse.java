package org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CTBNWFBv1RouteResponse {

	@JsonProperty("type")
	private String type;

	@JsonProperty("version")
	private String version;

	@JsonProperty("generated_timestamp")
	private String generatedTimestamp;

	@JsonProperty("data")
	private CTBNWFBv1RouteDto dto;

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

	public CTBNWFBv1RouteDto getDto() {
		return dto;
	}

	public void setDto(CTBNWFBv1RouteDto dto) {
		this.dto = dto;
	}

}
