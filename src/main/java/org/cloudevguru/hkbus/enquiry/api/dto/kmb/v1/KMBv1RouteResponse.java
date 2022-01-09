package org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KMBv1RouteResponse {

	@JsonProperty("type")
	private String type;

	@JsonProperty("version")
	private String version;

	@JsonProperty("generated_timestamp")
	private String generatedTimestamp;

	@JsonProperty("data")
	private KMBv1RouteDto dto;

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

	public KMBv1RouteDto getDto() {
		return dto;
	}

	public void setDto(KMBv1RouteDto dto) {
		this.dto = dto;
	}

}
